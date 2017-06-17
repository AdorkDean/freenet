package test;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.freenet.domain.Article;
import com.freenet.domain.NoticeArticle;
import com.freenet.domain.User;

public class articleTest {

	public static void main(String[] args) {
		Article article = new NoticeArticle();
		System.out.println(article.getClass().getSimpleName());
		System.out.println("{\"content\":\""+"啊哈哈哈哈"+"\",\"lastarticle\":\""+"喜欢声卡坏掉的"+"\",\"nextarticle\":\""+"富华大厦"+"\"}");
	
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("status","1");
		jsonMap.put("message","验证邮件已发送成功，请查收");
		JSONObject jsonObject = new JSONObject(jsonMap);
		System.out.println(jsonObject.toJSONString()) ;
	}
}
