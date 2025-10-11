package poly.edu.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import poly.edu.entity.Item;
import poly.edu.service.ShoppingCartService;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private Map<Integer, Item> map = new HashMap<>();

    // Thêm sản phẩm vào giỏ (theo id)
    @Override
    public Item add(Integer id) {
        Item item = map.get(id);
        if (item == null) {
            // Giả lập sản phẩm (thực tế sẽ lấy từ DB)
            item = new Item(id, "Sản phẩm " + id, 100.0, 1);
            map.put(id, item);
        } else {
            item.setQty(item.getQty() + 1);
        }
        return item;
    }

    // Xóa sản phẩm theo id
    @Override
    public void remove(Integer id) {
        map.remove(id);
    }

    // Cập nhật số lượng
    @Override
    public Item update(Integer id, int qty) {
        Item item = map.get(id);
        if (item != null) {
            item.setQty(qty);
        }
        return item;
    }

    // Xóa tất cả
    @Override
    public void clear() {
        map.clear();
    }

    // Lấy tất cả các item trong giỏ
    @Override
    public Collection<Item> getItems() {
        return map.values();
    }

    // Tổng số lượng
    @Override
    public int getCount() {
        return map.values().stream()
                .mapToInt(Item::getQty)
                .sum();
    }

    // Tổng tiền
    @Override
    public double getAmount() {
        return map.values().stream()
                .mapToDouble(item -> item.getPrice() * item.getQty())
                .sum();
    }
}
