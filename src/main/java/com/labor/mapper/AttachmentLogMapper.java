package com.labor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.labor.entity.AttachmentLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author BoCong
 * @date 2022/5/4
 */
@Mapper
public interface AttachmentLogMapper extends BaseMapper<AttachmentLog> {
    Integer insertAttachLog(AttachmentLog attachmentLog);

    /**
     * 根据contractFileId查询文件信息
     * @param contractFileId
     * @return
     */
    AttachmentLog selectByConFile(@Param("contractFileId") Long contractFileId);

    void deleteByContractFileId(Long contractFileId);
}
