package com.vikram.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vikram.exception.OrderException;
import com.vikram.model.Address;
import com.vikram.model.Cart;
import com.vikram.model.CartItem;
import com.vikram.model.Order;
import com.vikram.model.OrderItems;
import com.vikram.model.PaymentDetails;
import com.vikram.model.User;
import com.vikram.repository.AddressRepository;
import com.vikram.repository.CartRepository;
import com.vikram.repository.OrderItemRepository;
import com.vikram.repository.OrderRepository;
import com.vikram.repository.UserRepository;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	

	@Override
	public Order createOrder(User user, Address shippingAdress) {
	
		shippingAdress.setUser(user);
		Address address = addressRepository.save(shippingAdress);
		user.getAddress().add(address);
		userRepository.save(user);
		
		Cart cart = cartService.findUserCart(user.getId());
		List<OrderItems> orderItems = new ArrayList<>();
		
		for(CartItem item : cart.getCartItems()) {
			OrderItems orderItem = new OrderItems();
			
			orderItem.setPrice(item.getPrice());
			orderItem.setProduct(item.getProduct());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setSize(item.getSize());
			orderItem.setUserId(item.getUserId());
			orderItem.setDiscountedPrice(item.getDiscountedPrice());
			
			OrderItems createdOrderItems = orderItemRepository.save(orderItem);
			
			orderItems.add(createdOrderItems);
		}
		
		Order createdOrder = new Order();
		createdOrder.setUser(user);
		createdOrder.setOrderItems(orderItems);
		createdOrder.setTotalPrice(cart.getTotalPrice());
		createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
		createdOrder.setDiscount(cart.getDiscount());
		createdOrder.setTotalItem(cart.getTotalItem());
		
		createdOrder.setShippingAddress(address);
		createdOrder.setOrderDate(LocalDateTime.now());
		createdOrder.setOrderStatus("PENDING");
		
		//Initialize PaymentDetails and set status
		PaymentDetails paymentDetails = new PaymentDetails();
		paymentDetails.setStatus("PENDING");
		createdOrder.setPaymentDetails(paymentDetails);
		
		//createdOrder.getPaymentDetails().setStatus("PENDING");
		createdOrder.setCreatedAt(LocalDateTime.now());
		
		Order savedOrder=orderRepository.save(createdOrder);
		
		for(OrderItems item : orderItems) {
			item.setOrder(savedOrder);
			orderItemRepository.save(item);
		}
		
		return savedOrder;
	}

	@Override
	public Order findOrderById(Long orderId) throws OrderException {
		Optional<Order> opt = orderRepository.findById(orderId);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new OrderException("order not exist with id : "+orderId);
	}

	@Override
	public List<Order> usersOrderHistory(Long userId) {
		
		List<Order> orders =orderRepository.getUsersOrders(userId);
		
		return orders;
	}

	@Override
	public Order placedOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus("PLACED");
		order.getPaymentDetails().setStatus("COMPLETED");
		
		return order;
	}

	@Override
	public Order confirmedOrder(Long orderId) throws OrderException {
		
		Order order = findOrderById(orderId);
		order.setOrderStatus("CONFIRMED");
		
		
		return orderRepository.save(order);
	}

	@Override
	public Order shippedOrder(Long orderId) throws OrderException {
		
		Order order = findOrderById(orderId);
		order.setOrderStatus("SHIPPED");
		
		return orderRepository.save(order);
	}

	@Override
	public Order deliveredOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
				order.setOrderStatus("DELIVERED");
		return orderRepository.save(order);
	}

	@Override
	public Order canceledOrder(Long OrderId) throws OrderException {
		Order order = findOrderById(OrderId);
		order.setOrderStatus("CANCELLED");
		return orderRepository.save(order);
	}

	@Override
	public List<Order> getAllOrders() {
		List<Order> orders = orderRepository.findAll();
		return orders;
	}

	@Override
	public void deleteOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		//orderRepository.delete(order);
		orderRepository.deleteById(orderId);
		
	}

}
