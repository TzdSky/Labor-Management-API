package com.labor.utils;



import org.apache.commons.lang3.time.FastDateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.zip.CRC32;

/**
 * Created by boCong on 22/5/04.
 */
public class GenerateUtil {

    //FastDateFormat线性安全
    private static FastDateFormat dateFormat = FastDateFormat.getInstance("yyyyMMdd");

    /**
     * 生成唯一id
     *
     * @return
     */
    public static Integer generateId() {
        String uuid = UUID.randomUUID().toString();
        CRC32 crc32 = new CRC32();
        crc32.update(uuid.getBytes());
        int id = (int) crc32.getValue();
        if (id < 0) {
            id = -1 - id;
        }
        return id;
    }

    /**
     * 生成唯一id(LONG型)
     *
     * @return
     */
    public static Long generateLongId() {
        String uuid = UUID.randomUUID().toString();
        CRC32 crc32 = new CRC32();
        crc32.update(uuid.getBytes());
        return crc32.getValue();
    }



    /**
     * 生成信件流水号
     *
     * @param orgCode  部门组织编码
     * @param mailType 信件类型
     * @param serialNo 当天个数(4位)
     * @return 部门组织编码+网上信件(3)信件类型+办事号(8位日期+当天个数(4位))+校验码(3位数字随机数)
     */
    public static String generateApplyNo(String orgCode, String mailType, Long serialNo) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(orgCode);
        buffer.append(3);
        buffer.append(mailType);
        String eventCode = dateFormat.format(new Date()) + String.format("%04d", serialNo);
        buffer.append(eventCode);
        buffer.append(GenerateUtil.randomIn(3, 3));
        return buffer.toString();
    }

    /**
     * 生成查找密码
     *
     * @return 随机6位数字, 随机范围:大小写字母+数字
     */
    public static String generateQuerypwd() {
        return GenerateUtil.randomIn(6);
    }

    /**
     * 生成指定长度的随机数
     *
     * @param length 指定长度
     * @param scopes 随机范围,不传默认范围为大小写字母加数字;传1,表示范围为大写字母,传2,表示范围为小写,传3为数字;可传多值
     * @return
     */
    public static String randomIn(int length, int... scopes) {
        StringBuffer buffer = new StringBuffer();
        if (0 == scopes.length) {
            scopes = new int[]{1, 2, 3};
        }
        for (int i = 0; i < length; i++) {
            switch (scopes[(int) (Math.random() * scopes.length)]) {
                case 1:
                    buffer.append((char) (Math.random() * 26 + 65));
                    break;
                case 2:
                    buffer.append((char) (Math.random() * 26 + 97));
                    break;
                default:
                    buffer.append((char) (Math.random() * 10 + 48));
                    break;
            }
        }
        return buffer.toString();
    }

    /**
     * 生成15位流水号
     *
     * @param typeNum 类型数值 1-9
     * @return
     */
    public static String generateSerialNum(int typeNum) {
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        return typeNum + String.format("%014d", hashCodeV);
    }

    /**
     * 格式 YYMMDDHH+8位大写字母或数字
     *
     * @return
     */
    public static String buildCode() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYMMddHHmm");
        return dateFormat.format(new Date()) + GenerateUtil.randomIn(8, 1, 3);
    }

    public static void main(String[] args) {
        System.out.println(String.format("%04d", 123L));
        System.out.println(GenerateUtil.buildCode());
    }
}
