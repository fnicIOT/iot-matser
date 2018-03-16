import com.datastax.driver.core.utils.UUIDs;
import com.fnic.bean.RspBean;
import com.fnic.mybatis.thingsboard.model.AttributeKv;
import com.fnic.sysframe.security.Authority;
import com.fnic.sysframe.utils.JWTTokenUtil;
import com.google.common.collect.Maps;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.slf4j.Logger;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class TestObject {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void TestPasswdEncode(){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwd = "tenant";

        logger.debug(encoder.encode(passwd));
    }

    @Test
    public void TestRspData() {
        RspBean rspData = new RspBean();

        rspData.put("2222","22222");

        logger.debug("@@@@@@@");
    }

    @Test
    public void TestUUID() {
        UUID userId = UUIDs.timeBased();
        logger.debug("@@@@@@@:" + userId.toString() + "  :" + userId);
    }

    @Test
    public void TestEnum() {
        logger.debug("@@@@@@@:" + Authority.CUSTOMER_USER.toString());
    }


    @Test
    public void TestStream() {
        List<AttributeKv> list = Lists.newArrayList();

        AttributeKv attr = new AttributeKv();
        attr.setEntityType("DEVICE");
        attr.setEntityId("111");
        attr.setAttributeType("CL");
        attr.setAttributeKey("1");
        attr.setLongV(11L);
        list.add(attr);

        attr = new AttributeKv();
        attr.setEntityType("DEVICE");
        attr.setEntityId("111");
        attr.setAttributeType("CL");
        attr.setAttributeKey("2");
        attr.setLongV(22L);
        list.add(attr);

        attr = new AttributeKv();
        attr.setEntityType("DEVICE");
        attr.setEntityId("111");
        attr.setAttributeType("CL");
        attr.setAttributeKey("3");
        attr.setLongV(33L);
        list.add(attr);

        attr = new AttributeKv();
        attr.setEntityType("DEVICE");
        attr.setEntityId("111");
        attr.setAttributeType("CL");
        attr.setAttributeKey("4");
        attr.setLongV(44L);
        list.add(attr);

        attr = new AttributeKv();
        attr.setEntityType("DEVICE");
        attr.setEntityId("222");
        attr.setAttributeType("CL");
        attr.setAttributeKey("1");
        attr.setLongV(111L);
        list.add(attr);

        attr = new AttributeKv();
        attr.setEntityType("DEVICE");
        attr.setEntityId("222");
        attr.setAttributeType("CL");
        attr.setAttributeKey("2");
        attr.setLongV(222L);
        list.add(attr);

        attr = new AttributeKv();
        attr.setEntityType("DEVICE");
        attr.setEntityId("222");
        attr.setAttributeType("CL");
        attr.setAttributeKey("3");
        attr.setLongV(333L);
        list.add(attr);

        attr = new AttributeKv();
        attr.setEntityType("DEVICE");
        attr.setEntityId("222");
        attr.setAttributeType("CL");
        attr.setAttributeKey("4");
        attr.setLongV(444L);
        list.add(attr);

        Map<String,List<AttributeKv>> item = list.stream().collect(Collectors.groupingBy(AttributeKv::getEntityId));

        List<Map<String,Object>> tempList = Lists.newArrayList();
        for ( Map.Entry me : item.entrySet()) {
            Map<String,Object> temp = Maps.newHashMap();
            logger.debug(me.getKey() + " : " );
            temp.put("id",me.getKey());
            for(AttributeKv attributeKv : (List<AttributeKv>) me.getValue() ) {
                //logger.debug(attributeKv.getAttributeKey() + " : " +  attributeKv.getLongV());
                temp.put(attributeKv.getAttributeKey(),attributeKv.getLongV());
            }

            tempList.add(temp);
        }
    }

    @Test
    public void TestToken() {
        Map<String,Object> claims = Maps.newHashMap();

        claims.put("key","33333");

        String token = JWTTokenUtil.generateToken(claims);

        try {
            Thread.sleep(1);
            //Claims claims2 = JWTTokenUtil.getClaimsFromToken(token);


            logger.debug("@@@@@:" + JWTTokenUtil.isTokenExpired(token));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void TestTime() {
        logger.debug("" + new Date(System.currentTimeMillis() + 60 * 60 * 24 * 15 * 1000));
    }
}
