package com.child.programming.base.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 鏈伐鍏风被鏄鐓sonUtils鐨勬柊json宸ュ叿绫伙紝鍥犱负缂撳瓨鐨勫姞鍏ワ紝缁忚繃娴嬭瘯锛屾渶鍚庨�夋嫨jackson2.6浣滀负json鏂板鐞嗙被銆�
 * jackson鎻愪緵浜嗙洿鎺ヨ浆鎹ist锛宮ap绛変竴绯诲垪鏂规硶锛屽苟鏋佸ぇ鎻愰珮浜唈son杞崲鏁堢巼锛屽ぇ瀹跺彲瀵规瘮鎰熷彈涓�涓嬨��
 * @author dyh
 *
 */
public class NewJsonUtils {
	
	private static JsonGenerator jsonGenerator = null;
    private static ObjectMapper objectMapper = null;
    
    /**
     * 灏嗗璞�(bean锛宭ist, map绛�)杞崲鎴恓son瀛楃涓�
     * @param obj
     * @return
     * @throws Exception
     */
    public static String bean2JsonStr(Object obj){
    	objectMapper = new ObjectMapper();
    	StringWriter writer =new StringWriter();
    	try {
			jsonGenerator = objectMapper.getFactory().createGenerator(writer);
			jsonGenerator.writeObject(obj);
			return writer.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}  	
    }
    
    /**
     * 璁瞛son瀛楃涓茶浆鎹㈡垚鍗曚釜鐨刯ava瀵硅薄锛岄渶瑕佷紶鍏ava绫�
     * @param jsonStr
     * @param clazz
     * @return
     */
    public static Object jsonStr2Bean(String jsonStr, Class<?> clazz){
    	objectMapper = new ObjectMapper();
    	objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    	objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    	try {
			return objectMapper.readValue(jsonStr, clazz);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * 灏唈son瀛楃涓茶浆鎹㈡垚java闆嗗悎(list.map绛夌瓑)
     * @param jsonStr
     * @param beanClazz
     * @param typeClazz
     * @return
     */
	public static Collection<?> jsonStr2List(String jsonStr, Class<?> beanClazz){
    	objectMapper = new ObjectMapper();
    	try {
    		return objectMapper.readValue(jsonStr, objectMapper.getTypeFactory().constructCollectionType(List.class, beanClazz));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
	
	/**
	 * 鎶妀son瀛楃涓茶浆鍖栨垚涓簃ap瀵硅薄
	 * @param jsonStr
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map json2Map(String jsonStr){
		objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(jsonStr, HashMap.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
		
		   
	}
	
	public static String List2JsonStr(List<?> list){
		objectMapper = new ObjectMapper();

		try {
			return objectMapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
    
//    @SuppressWarnings("unchecked")
//	public static void main(String[] args) throws Exception{
//    	List<TbDict> list = new ArrayList<TbDict>();
//    	TbDict dict = new TbDict();
//    	dict.setName("娴嬭瘯");
//    	dict.setCode("test");
//    	list.add(dict);
//    	String jsonStr = bean2JsonStr(list);
//    	List<TbDict> list1 = (List<TbDict>) jsonStr2List(jsonStr, TbDict.class, List.class);
//    	for(TbDict dict1 : list1){
//    		System.out.println(dict1.getCode());
//    	}
//    	
//    }

}
