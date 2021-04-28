package com.omad.lee.damo.Service.ServiceImp;

import com.omad.lee.damo.Model.Entity.*;
import com.omad.lee.damo.Model.Req.QuestionReq;
import com.omad.lee.damo.Model.Req.VariantsReq;
import com.omad.lee.damo.Model.Req.FinishTest;
import com.omad.lee.damo.Model.Resp.AddTestingResp;
import com.omad.lee.damo.Model.Resp.TestingResp;
import com.omad.lee.damo.Model.Resp.QuestionResp;
import com.omad.lee.damo.Model.Resp.VariantsResp;
import com.omad.lee.damo.Model.Responce.Utils;
import com.omad.lee.damo.Repository.*;
import com.omad.lee.damo.Service.QuestionService;
import org.checkerframework.checker.units.qual.A;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    public  QuestionRepository questionRepository;

    @Autowired
    public TestsRepository testsRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public VariantRepository variantRepository;

    @Autowired
    public HistoryRepository historyRepository;

    @Autowired
    public  Utils utils;

    @Override
    public List<QuestionResp> getAll(int page) {
        page--;
        Pageable pageable= PageRequest.of(page, 12);
        Page<Questions> pages= questionRepository.findAll(pageable);
        List<QuestionResp> list1=new ArrayList<>();
        for (Questions questions: pages.getContent()){
            QuestionResp questionResp=new QuestionResp();
            List<VariantsResp> list2=new ArrayList<>();
            for (Variants variants: questions.getVariants()){
                VariantsResp variantsResp=new VariantsResp();
                BeanUtils.copyProperties(variants, variantsResp);
                list2.add(variantsResp);
            }
            BeanUtils.copyProperties(questions, questionResp);
            questionResp.setVariantsRespList(list2);
            list1.add(questionResp);
        }
        return list1;
    }

    @Override
    public QuestionResp changeQuestion(String questionid, QuestionReq questionReq) {
        ModelMapper modelMapper=new ModelMapper();
        Questions questions = modelMapper.map(questionReq, Questions.class);
        QuestionResp questionResp = modelMapper.map(questionReq, QuestionResp.class);
        questionRepository.save(questions);
        return questionResp;
    }

    @Override
    public String deleteQuestion(String questionid) {
        Questions questions = questionRepository.findQuestionsByQuestionid(questionid);
        if (questions == null) throw new UsernameNotFoundException(questionid);
        questionRepository.delete(questions);
        return questionid;
    }

    @Override
    public QuestionResp createQuestion(QuestionReq questionReq) {
        Questions questions=new Questions();
        List<Variants> list=new ArrayList<>();
        for (VariantsReq variantsReq: questionReq.getVariantsReqs()){
            Variants variants=new Variants();
            BeanUtils.copyProperties(variantsReq,variants);
            variants.setVariantid(utils.generatedAddressId(50));
            list.add(variants);
        }
        BeanUtils.copyProperties(questionReq, questions);
        questions.setQuestionid(utils.generatedAddressId(40));
        questions.setVariants(list);
        questionRepository.save(questions);
        ModelMapper modelMapper=new ModelMapper();
        QuestionResp questionResp =modelMapper.map(questions, QuestionResp.class);
        return questionResp;
    }

    @Override
    public TestingResp finishTest(List<FinishTest> list, String email) {
        SimpleDateFormat format=new SimpleDateFormat();
        Date date=new Date(String.valueOf(format));

        UserEntity userEntity = userRepository.findUserByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);

        TestingResp testingResp =new TestingResp();
        List<AddTestingResp> addTestingResps=new ArrayList<>();

        History history =new History();
        Testings testings=new Testings();
        List<HistoryList> lists= new ArrayList<>();
        int a=0;
        int b=0;
        for (FinishTest finishTest: list){

            HistoryList historyList=new HistoryList();
            AddTestingResp addTestingResp=new AddTestingResp();
            QuestionResp questionResp=new QuestionResp();
            VariantsResp variantsResp=new VariantsResp();

            Questions questions = questionRepository.findQuestionsByQuestionid(finishTest.getQuestionid());
            if (questions == null) throw new UsernameNotFoundException(finishTest.getQuestionid());
            Variants variants = variantRepository.findVariantsByVariantid(finishTest.getVariantid());
            if (variants == null) throw new UsernameNotFoundException(finishTest.getVariantid());

            historyList.setQuestionid(finishTest.getQuestionid());
            historyList.setVariantid(finishTest.getVariantid());
            ModelMapper modelMapper=new ModelMapper();
            questionResp = modelMapper.map(questions, QuestionResp.class);
            BeanUtils.copyProperties(variants, variantsResp);

            addTestingResp.setQuestionResps(questionResp);
            addTestingResp.setVariantsResp(variantsResp);
            List<Variants> variant = questions.getVariants();

            for(int i=0;i<variant.size();i++){
                if(variant.get(i).getVariantid()== variants.getVariantid()){
                    if (variant.get(i).isAnswer()) {
                        a++;
                        addTestingResp.setTrueOrNot(true);
                    }
                    else{
                        addTestingResp.setTrueOrNot(false);
                    }
                }
            }
            addTestingResps.add(addTestingResp);
            b += questions.getVariants().stream().filter(variants1 -> variants1.isAnswer()).count();
            lists.add(historyList);
        }
       testings.setHistoryListList(lists);
       testings.setTrues(a);
       testings.setDatebegin( (java.sql.Date) date);
       testings.setDateend((java.sql.Date) date);

       testingResp.setDatebegin(testings.getDatebegin());
       testingResp.setDateend(testings.getDateend());
       testingResp.setTrues(a);
       testingResp.setAddTestingResp(addTestingResps);

       List<Testings> testingsList=history.getTests();
       testingsList.add(testings);
       history.setTests(testingsList);

       history.setUserEntity(userEntity);
       userEntity.setHistory(history);
       userRepository.save(userEntity);
       testings.setHistory(history);
       historyRepository.save(history);
       testsRepository.save(testings);

       ModelMapper modelMapper=new ModelMapper();
       testingResp = modelMapper.map(testings, TestingResp.class);

        return testingResp;
    }

    @Override
    public QuestionResp getOneQuestion(String questionid) {
       Questions questions = questionRepository.findQuestionsByQuestionid(questionid);
        ModelMapper modelMapper=new ModelMapper();
        QuestionResp questionResp = modelMapper.map(questions, QuestionResp.class);;
        return questionResp;
    }


}
