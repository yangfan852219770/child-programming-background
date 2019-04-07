package com.child.programming.base.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 
 * @ClassName:  BASE64DecodedMultipartFile   
 * @Description: 鑷畾涔夌殑MultipartFile鐨勫疄鐜扮被锛屼富瑕佺敤浜巄ase64涓婁紶鏂囦欢锛屼互涓嬫柟娉曢兘鍙互鏍规嵁瀹為檯椤圭洰鑷瀹炵幇
 * @author: 娌冲寳鐪佸缓绛戠瀛︾爺绌堕櫌鍒涚爺宸ヤ綔瀹� - 鏉ㄥ竼 
 * @date:   2018骞�11鏈�19鏃� 涓嬪崍3:46:04   
 */
public class BASE64DecodedMultipartFile implements MultipartFile {
 
    private final byte[] imgContent;
    private final String header;
    private final String contentType;
 
    public BASE64DecodedMultipartFile(byte[] imgContent, String header) {
        this.imgContent = imgContent;
        this.header = header.split(";")[0];
        this.contentType = this.getContentType();
    }
 
    @Override
    public String getName() {
    	return "";
    }
 
    @Override
    public String getOriginalFilename() {
        return "";
    }
 
    @Override
    public String getContentType() {
    	//鏁版嵁寮傚父锛�
    	String[] contentTypes = header.split(":")[1].split("/");
        return new StringBuffer().append(".")
        		.append(contentTypes[1])
        		.toString();
    }
 
    @Override
    public boolean isEmpty() {
        return imgContent == null || imgContent.length == 0;
    }
 
    @Override
    public long getSize() {
        return imgContent.length;
    }
 
    @Override
    public byte[] getBytes() throws IOException {
        return imgContent;
    }
 
    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(imgContent);
    }
 
    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
    	FileOutputStream out = null;
    	try {
    		out = new FileOutputStream(dest);
    		out.write(imgContent);
    	}finally {
    		out.close();
		}
    }
    
}