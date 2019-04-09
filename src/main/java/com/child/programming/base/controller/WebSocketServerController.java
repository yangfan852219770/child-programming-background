package com.child.programming.base.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.OLAMIUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zdp
 * @description: TODO
 */
@Component
@ServerEndpoint("/websocket/{userId}")
@Log4j2
public class WebSocketServerController {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static ConcurrentHashMap<String, WebSocketServerController> websocketList = new ConcurrentHashMap<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //接收userId
    private String userId="";
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("userId") String userId) {
        this.session = session;
        this.userId=userId;
        websocketList.put(userId,this);     //加入map
        addOnlineCount();           //在线数加1
        log.info("有新连接加入！当前在线人数为" + getOnlineCount());
        try {
            sendMessage("您好，我是客服小贝，有什么可以帮到您的吗？");
        } catch (IOException e) {
            log.error("websocket IO异常");
        }
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if(websocketList.get(this.userId)!=null) {
            websocketList.remove(this.userId);
            //webSocketSet.remove(this);  //从set中删除
            subOnlineCount();           //在线数减1
            log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口"+userId+"的信息:"+message);
        if(!EmptyUtils.stringIsEmpty(message)){
            JSONArray list=JSONArray.parseArray(message);
            for (int i = 0; i < list.size(); i++) {
                try {
                    //解析发送的报文
                    JSONObject object = list.getJSONObject(i);
                    String toUserId=object.getString("id");
                    String contentText=object.getString("content");
                    object.put("fromUserId",this.userId);
                    //传送给对应用户的websocket
                    if(!EmptyUtils.stringIsEmpty(toUserId)&&!EmptyUtils.stringIsEmpty(contentText)){
                        WebSocketServerController socketx=websocketList.get(userId);
                        if(!EmptyUtils.objectIsEmpty(socketx)){
                        String[] participle=OLAMIUtil.OLAMIParticiple(contentText);
                        boolean flag=true;
                        if(EmptyUtils.arrayIsEmpty(participle))
                            socketx.sendMessage("小贝正在赶来的路上，请您稍后...");
                        for (String str:participle
                             ) {
                            switch (str){
                                case "收费":
                                    socketx.sendMessage("我们教育机构很不错，如果想更多的了解，请加微信：15833655291，他会第一时间回复您");
                                    flag=false;
                                    break;
                                case "叫":
                                    socketx.sendMessage("我叫小贝");
                                    flag=false;
                                    break;
                                case "名称":
                                    socketx.sendMessage("小贝编程");
                                    flag=false;
                                    break;
                            }

                        }
                        if(flag){
                            flag=true;
                            //OLAMI智能回复
                            socketx.sendMessage(OLAMIUtil.OLAMIAnswer(contentText));
                        }

                            //此处可以放置相关业务代码，例如存储到数据库
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }


    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 群发自定义消息
     * */
//    public static void sendInfo(String message,@PathParam("userId") String userId) throws IOException {
//        log.info(message);
//        for (WebSocketServer item : webSocketSet) {
//            try {
//                item.sendMessage(message);
//            } catch (IOException e) {
//                continue;
//            }
//        }
//    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServerController.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServerController.onlineCount--;
    }

}
