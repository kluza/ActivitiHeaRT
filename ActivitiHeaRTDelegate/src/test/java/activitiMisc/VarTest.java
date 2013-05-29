package activitiMisc;

import static org.junit.Assert.assertNotNull;
import java.util.Arrays;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

/** @author ja */
public class VarTest {
    
    @Test
    public void test() {
        org.apache.log4j.BasicConfigurator.configure();
        ProcessEngine pEngine =
                ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration()
                        .buildProcessEngine();
        RepositoryService repService = pEngine.getRepositoryService();
        RuntimeService runService = pEngine.getRuntimeService();
        Deployment d =
                repService.createDeployment().addClasspathResource("procDefs/VarTest.bpmn20.xml").deploy();
        assertNotNull(d);
        ProcessInstance pInstance = runService.startProcessInstanceByKey("VarTest");
        Arrays.asList("dupa", "dupa", "blebleble");
        assertNotNull(pInstance);
    }
    
}
