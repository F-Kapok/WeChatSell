package com.fans.service.impl;

import com.fans.config.WeChatConstant;
import com.fans.dto.OrderDto;
import com.fans.service.interfaces.IPushMessageService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @ClassName PushMessageServiceImpl
 * @Description:
 * @Author fan
 * @Date 2019-01-16 09:45
 * @Version 1.0
 **/
@Service(value = "iPushMessageService")
@Slf4j
public class PushMessageServiceImpl implements IPushMessageService {
    @Resource(name = "wxMpService")
    private WxMpService wxMpService;
    @Resource(name = "weChatConstant")
    private WeChatConstant weChatConstant;

    @Override
    public void orderStatusPush(OrderDto orderDto) {
        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        wxMpTemplateMessage.setTemplateId(weChatConstant.getTemplateId().get("orderStatus"));
        wxMpTemplateMessage.setToUser(orderDto.getBuyerOpenid());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<WxMpTemplateData> data = Lists.newArrayList(
                new WxMpTemplateData("first", "亲，请记得收货。"),
                new WxMpTemplateData("keyword1", orderDto.getOrderId()),
                new WxMpTemplateData("keyword2", "￥" + orderDto.getOrderAmount()),
                new WxMpTemplateData("keyword3", orderDto.getOrderStatusEnum().getDesc()),
                new WxMpTemplateData("keyword4", sf.format(orderDto.getCreateTime())),
                new WxMpTemplateData("remark", "欢迎再次光临！")
        );
        wxMpTemplateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
        } catch (WxErrorException e) {
            log.error("【微信模版消息】发送失败, {}", e);
        }
    }
}
