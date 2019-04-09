package com.child.programming.base.util;


import ai.olami.cloudService.APIConfiguration;
import ai.olami.cloudService.APIResponse;
import ai.olami.cloudService.TextRecognizer;
import ai.olami.nli.NLIResult;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * @author zdp
 * @description: TODO
 */
public class OLAMIUtil {

    // 设置您的 Key 相关信息
    private static String myAppKey = "4e1bab85f39240d28c9fc1c085236ada";
    private static String myAppSecret = "13bb9a8d78284547ab948fce106ad942";

    public static String OLAMIAnswer(String clientsMsg) {

        NLIResult[] nliResults = null;
        // 创建文本识别器对象
        TextRecognizer recoginzer = createOLAMI();

        // 请求 客户消息 的 NLI 语义分析或 IDS 智能答复与数据
        APIResponse response2 = null;
        try {
            response2 = recoginzer.requestNLI(clientsMsg);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 检查请求结果
        if (response2.ok() && response2.hasData()) {
            // 取得 NLI 语义分析或 IDS 智能答复与数据
            nliResults = response2.getData().getNLIResults();
        }
        return nliResults.length > 0 ? nliResults[0].getDescObject().getReplyAnswer() : "小贝正在赶来的路上请稍等";

    }

    public static String[] OLAMIParticiple(String clientsMsg) {
        String[] ws = null;
        // 创建文本识别器对象
        TextRecognizer recoginzer = createOLAMI();
        // 请求 "今天星期几" 的分词结果
        APIResponse response1 = null;
        try {
            response1 = recoginzer.requestWordSegmentation(clientsMsg);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 检查请求结果
        if (response1.ok() && response1.hasData()) {
            // 取得分词结果
            ws = response1.getData().getWordSegmentation();
        }
        return ws;
    }

    private static TextRecognizer  createOLAMI(){
        // 创建 APIConfiguration 对象
        APIConfiguration config = new APIConfiguration(myAppKey, myAppSecret, APIConfiguration.LOCALIZE_OPTION_SIMPLIFIED_CHINESE);
        // 创建文本识别器对象
        TextRecognizer recoginzer = new TextRecognizer(config);
        return recoginzer;
    }
}
