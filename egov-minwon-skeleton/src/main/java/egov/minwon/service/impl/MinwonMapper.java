package egov.minwon.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import egov.minwon.service.MinwonVO;

public interface MinwonMapper {

    List<MinwonVO> selectMinwonList();

    int insertMinwon(MinwonVO minwonVO);

    int updateMinwonStatus(@Param("id") Long id, @Param("status") String status);
}
