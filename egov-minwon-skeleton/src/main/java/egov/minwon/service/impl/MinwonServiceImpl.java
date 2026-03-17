package egov.minwon.service.impl;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egov.minwon.service.MinwonService;
import egov.minwon.service.MinwonVO;

@Service("minwonService")
public class MinwonServiceImpl extends EgovAbstractServiceImpl implements MinwonService {

    private final MinwonMapper minwonMapper;

    public MinwonServiceImpl(MinwonMapper minwonMapper) {
        this.minwonMapper = minwonMapper;
    }

    @Override
    public List<MinwonVO> selectMinwonList() {
        return minwonMapper.selectMinwonList();
    }

    @Override
    @Transactional
    public void insertMinwon(MinwonVO minwonVO) {
        minwonMapper.insertMinwon(minwonVO);
    }

    @Override
    @Transactional
    public void updateMinwonStatus(Long id, String status) {
        minwonMapper.updateMinwonStatus(id, status);
    }
}
