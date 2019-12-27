import com.ocean.CaseApplication;
import com.ocean.strategy.StrategyContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CaseApplication.class)
public class StrategyContextTest {
    @Autowired
    private StrategyContext strategyContext;


    @Test
    public void calculatePrice() {
        Double general = strategyContext.discount("generalMember");
        System.out.println("普通用户，折扣率：" + general.toString());
        Double vip = strategyContext.discount("vipMember");
        System.out.println("普通会员，折扣率：" + vip.toString());
        Double superVip = strategyContext.discount("superVipMember");
        System.out.println("超级会员，折扣率：" + superVip.toString());
    }

}
