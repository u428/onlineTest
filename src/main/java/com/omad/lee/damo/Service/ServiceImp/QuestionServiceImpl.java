package com.omad.lee.damo.Service.ServiceImp;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.omad.lee.damo.Model.Entity.Questions;
import com.omad.lee.damo.Model.Entity.Variants;
import com.omad.lee.damo.Model.Req.QuestionReq;
import com.omad.lee.damo.Model.Req.VariantsReq;
import com.omad.lee.damo.Model.Resp.FinishTestResp;
import com.omad.lee.damo.Model.Resp.QuestionResp;
import com.omad.lee.damo.Model.Resp.VariantsResp;
import com.omad.lee.damo.Model.Responce.Utils;
import com.omad.lee.damo.Repository.QuestionRepository;
import com.omad.lee.damo.Service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    public  QuestionRepository questionRepository;

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
    public void changeQuestion(String questionid) {

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
            list.add(variants);
        }
        BeanUtils.copyProperties(questionReq, questions);
        questions.setQuestionid(utils.generatedAddressId(40));
        questions.setVariants(list);
        questionRepository.save(questions);
    }

    @Override
    public List<FinishTestResp> finishTest() {
        return null;
    }
}
