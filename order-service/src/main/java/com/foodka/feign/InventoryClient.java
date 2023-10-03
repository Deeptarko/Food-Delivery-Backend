package com.foodka.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.foodka.dto.InventoryDto;

@FeignClient(value = "inventoryClient", url = "http://localhost:8001/inventory")
public interface InventoryClient {

	@RequestMapping(method = RequestMethod.GET, value = "/checkQuantity/{itemIds}")
	List<InventoryDto> inventoryItems(@PathVariable("itemIds") List<Long> itemIds);
}
