package com.icloud.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icloud.entity.Item;
import com.icloud.entity.Order;
import com.icloud.response.Response;
import com.icloud.respository.ItemRepo;
import com.icloud.respository.OrderRepo;

@RestController
@RequestMapping("/home")
public class OrderController {
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private ItemRepo itemRepo;
	
	@Autowired
	private Order order;
	
	@Autowired
	private Item item;
	
	@Autowired
	private Response response;
	
	List<Item> items;
	
	List<Order> orders;
	
	@GetMapping("/displayAll")
	public ResponseEntity<Object> getAll(){
		orders=new ArrayList<Order>();
		try {
			orders=orderRepo.findAll();
			for(int i=0;i<this.orders.size();i++) {
				response.setOrderDate(orders.get(i).getOrderDate());
				response.setOrderId(orders.get(i).getOrderId());
				response.setItems(orders.get(i).getItems());
				if(null != this.order.getOrderStatus())
					response.setOrderStatus(this.order.getOrderStatus());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@GetMapping("/display/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id){
		this.orders=new ArrayList<Order>();
		try {
			this.orders=orderRepo.findByOrderId(id);
			response.setOrderDate(orders.get(0).getOrderDate());
			response.setOrderId(orders.get(0).getOrderId());
			response.setItems(orders.get(0).getItems());
			if(null != this.order.getOrderStatus())
				response.setOrderStatus(this.order.getOrderStatus());
			
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@PostMapping("/saveorder")
	public ResponseEntity<Object> saveOrder(@RequestBody Order order){
		this.order.setOrderDate(order.getOrderDate());
		this.order.setItems(order.getItems());
		if(null != this.order.getOrderStatus())
			this.order.setOrderStatus(order.getOrderStatus());
		for(int i=0;i<order.getItems().size();i++) {
			items.add(order.getItems().get(0));
			items.add(order.getItems().get(1));
			items.add(order.getItems().get(2));
		}
		try {
			orderRepo.save(this.order);
			itemRepo.save(this.item);
			response.setOrderId(this.order.getOrderId());
			response.setItems(this.items);
			response.setOrderDate(this.order.getOrderDate());
			if(null != this.order.getOrderStatus())
				response.setOrderStatus(this.order.getOrderStatus());
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
