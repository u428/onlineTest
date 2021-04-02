package com.omad.lee.damo.Service.ServiceImp;

import com.omad.lee.damo.Model.Entity.*;
import com.omad.lee.damo.Model.Req.QuestionReq;
import com.omad.lee.damo.Model.Req.VariantsReq;
import com.omad.lee.damo.Model.Req.FinishTest;
import com.omad.lee.damo.Model.Resp.HistoryResp;
import com.omad.lee.damo.Model.Resp.QuestionResp;
import com.omad.lee.damo.Model.Resp.VariantsResp;
import com.omad.lee.damo.Model.Responce.Utils;
import com.omad.lee.damo.Repository.*;
import com.omad.lee.damo.Service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public void changeQuestion(String questionid, QuestionReq questionReq) {
        ModelMapper modelMapper=new ModelMapper();
        Questions questions = modelMapper.map(questionReq, Questions.class);
//        BeanUtils.copyProperties(questionReq, questions);
        questionRepository.save(questions);
    }

    @Override
    public void deleteQuestion(String questionid) {
        Questions questions = questionRepository.findQuestionsByQuestionid(questionid);
        questionRepository.delete(questions);
    }

    @Override
    public void createQuestion(QuestionReq questionReq) {
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
    }

    @Override
    public HistoryResp finishTest(List<FinishTest> list, String email) {
        SimpleDateFormat format=new SimpleDateFormat();
        Date date=new Date(String.valueOf(format));
        UserEntity userEntity = userRepository.findUserByEmail(email);
        History history =new History();
        Testings testings=new Testings();
        List<HistoryList> lists= new ArrayList<>();
        int a=0;
        int b=0;
        for (FinishTest finishTest: list){
            HistoryList historyList=new HistoryList();
            historyList.setQuestionid(finishTest.getQuestionid());
            historyList.setVariantid(finishTest.getVariantid());
            Questions questions = questionRepository.findQuestionsByQuestionid(finishTest.getQuestionid());
            Variants variants = variantRepository.findVariantsByVariantid(finishTest.getVariantid());
            List<Variants> variant = questions.getVariants();
            for(int i=0;i<variant.size();i++){
                if(variant.get(i).getVariantid()== variants.getVariantid() && variant.get(i).isAnswer()){
                    a++;
                }
            }
            b += questions.getVariants().stream().filter(variants1 -> variants1.isAnswer()).count();
            System.out.println(b);
            lists.add(historyList);
        }
       testings.setHistoryListList(lists);
       testings.setTrues(a);
       testings.setDatebegin( (java.sql.Date) date);
       testings.setDateend((java.sql.Date) date);
       List<Testings> testingsList=history.getTests();
       testingsList.add(testings);
       history.setTests(testingsList);
       UserEntity entity = userRepository.findUserByEmail(email);
       history.setUserEntity(entity);
       userEntity.setHistory(history);
       userRepository.save(entity);
       testings.setHistory(history);
       historyRepository.save(history);
       testsRepository.save(testings);


       ModelMapper modelMapper=new ModelMapper();
       HistoryResp historyResp=new HistoryResp();
       historyResp = modelMapper.map(history, HistoryResp.class);


        return historyResp;


    }

    @Override
    public QuestionResp getOneQuestion(String questionid) {
       Questions questions = questionRepository.findQuestionsByQuestionid(questionid);
        ModelMapper modelMapper=new ModelMapper();
        QuestionResp questionResp = modelMapper.map(questions, QuestionResp.class);;
        return questionResp;
    }


}
