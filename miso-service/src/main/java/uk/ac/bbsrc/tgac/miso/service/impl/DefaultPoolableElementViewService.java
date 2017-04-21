package uk.ac.bbsrc.tgac.miso.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.ac.bbsrc.tgac.miso.core.data.impl.view.PoolableElementView;
import uk.ac.bbsrc.tgac.miso.core.util.PaginationFilter;
import uk.ac.bbsrc.tgac.miso.persistence.PoolableElementViewDao;
import uk.ac.bbsrc.tgac.miso.service.PoolableElementViewService;

@Service
@Transactional(rollbackFor = Exception.class)
public class DefaultPoolableElementViewService implements PoolableElementViewService {
  @Autowired
  private PoolableElementViewDao poolableElementViewDao;

  @Override
  public long count(PaginationFilter... filter) throws IOException {
    return poolableElementViewDao.count(filter);
  }

  @Override
  public List<PoolableElementView> list(int offset, int limit, boolean sortDir, String sortCol, PaginationFilter... filter)
      throws IOException {
    return poolableElementViewDao.list(offset, limit, sortDir, sortCol, filter);
  }

}