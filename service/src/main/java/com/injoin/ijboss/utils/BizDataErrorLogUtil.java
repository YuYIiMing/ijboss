package com.injoin.ijboss.utils;


import com.injoin.ijboss.exception.BizDataException;
import com.injoin.ijboss.utils.bean.BizDataBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author figo
 */
public class BizDataErrorLogUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(BizDataErrorLogUtil.class);

    public static void log(BizDataBean bizDataBean, BizDataException bizDataException) {
        bizDataException.printStackTrace();
        LOGGER.error("bizData error,Please handle it as soon as possible,exceptionMessage:{},bizDataCode:{},paramData:{}", bizDataException.getMessage(), bizDataBean.getBizDataCode(), bizDataBean.getParamData());
    }
}
