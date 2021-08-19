package cn.dizent.week08xa.service.impl;

import cn.dizent.week08xa.entity.Product;
import cn.dizent.week08xa.mapper.xadb.ProductMapper;
import cn.dizent.week08xa.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dizent
 * @since 2021-08-15
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
