package com.ibothub.heap.flowable.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.ui.modeler.domain.AbstractModel;
import org.flowable.ui.modeler.rest.api.ApiModelResource;
import org.flowable.ui.modeler.rest.app.ModelResource;
import org.flowable.ui.modeler.rest.app.ModelsResource;
import org.flowable.ui.modeler.serviceapi.ModelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jakarta.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/3/2 19:52
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@Slf4j
public class ModelServiceTests {

    @Resource
    ModelService modelService;

    @Test
    public void testGetModelsByModelType(){
        List<AbstractModel> list = modelService.getModelsByModelType(AbstractModel.MODEL_TYPE_BPMN);
        list.forEach(abstractModel -> {
            log.debug("{} -> {}", abstractModel.getName(), abstractModel.getCreatedBy());

            byte[] bpmnXML = modelService.getBpmnXML(abstractModel);
            try {
                System.out.println(IOUtils.toString(bpmnXML, StandardCharsets.UTF_8.name()));
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }


}
