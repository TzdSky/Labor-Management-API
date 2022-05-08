package com.labor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.labor.entity.AttachmentLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author BoCong
 * @date 2022/5/4
 */
@Mapper
public interface AttachmentLogMapper extends BaseMapper<AttachmentLog> {
    Integer insertAttachLog(AttachmentLog attachmentLog);
}
