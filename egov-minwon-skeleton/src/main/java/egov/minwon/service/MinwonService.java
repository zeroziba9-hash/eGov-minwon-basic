package egov.minwon.service;

import java.util.List;

public interface MinwonService {

    List<MinwonVO> selectMinwonList();

    void insertMinwon(MinwonVO minwonVO);

    void updateMinwonStatus(Long id, String status);
}
