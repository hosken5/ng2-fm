import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by hongpf on 16/9/27.
 */
public class HelloJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        RestTemplate restTemplate  =  new RestTemplate();

        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap() ;

        String url  = jobDataMap.getString("url");
        String result =  restTemplate.getForObject(url,String.class) ;
        System.out.println(result) ;
    }
}