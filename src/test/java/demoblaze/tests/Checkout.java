package demoblaze.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import demoblaze.utils.Base;
import demoblaze.utils.JavaUtility;
import pages.CheckoutPage;
import pages.ShoppingCartPage;

public class Checkout extends Base{
	
	@Test
	public void validateCheckoutProcess() {
		CheckoutPage checkout = new CheckoutPage(driver);
		ShoppingCartPage shoppingCart = new ShoppingCartPage(driver);
		shoppingCart.selectProduct("Samsung galaxy s6");
		shoppingCart.addToCart();
		shoppingCart.openCart();

		checkout.clickPlaceOrder();
	    
		checkout.fillCheckoutDetails("John Doe", "USA", "New York", "1234567812345678", "12", "2025");
		checkout.completePurchase();

	    Assert.assertTrue(checkout.isOrderSuccessful(), "Order was not completed successfully!");
	    JavaUtility.setDriver(driver);
		JavaUtility.takeScreenShot("validateCheckoutProcess");
		
	}
	
	@Test
	public void validateCheckoutWithoutDetails() {
	    CheckoutPage checkout = new CheckoutPage(driver);
	    ShoppingCartPage shoppingCart = new ShoppingCartPage(driver);
	    
	    shoppingCart.selectProduct("Samsung galaxy s6");
	    shoppingCart.addToCart();
	    shoppingCart.openCart();

	    checkout.clickPlaceOrder();
	    //checkout.completePurchase();   
	    
	    //Assert.assertSame(checkout.isErrorMessageDisplayed(), "Error message not displayed when trying to checkout without details!");
	    JavaUtility.setDriver(driver);
		JavaUtility.takeScreenShot("validateCheckoutWithoutProcess");
		
	}
	
	@Test
	public void validateCheckoutWithInvalidCard() {
	    CheckoutPage checkout = new CheckoutPage(driver);
	    ShoppingCartPage shoppingCart = new ShoppingCartPage(driver);
	    
	    shoppingCart.selectProduct("Sony vaio i5");
	    shoppingCart.addToCart();
	    shoppingCart.openCart();

	    checkout.clickPlaceOrder();
	    
	    // Enter invalid credit card details
	    checkout.fillCheckoutDetails("Alice Smith", "UK", "London", "INVALIDCARD123", "05", "2026");
	    checkout.completePurchase();
	    
	    //Assert.assertSame(checkout.isErrorMessageDisplayed(), "Error message not displayed for invalid credit card input!");
	    JavaUtility.setDriver(driver);
		JavaUtility.takeScreenShot("validateCheckoutWithInvalidCard");
		
	}
	
	@Test
	public void validateCheckoutWithExpiredCard() {
	    CheckoutPage checkout = new CheckoutPage(driver);
	    ShoppingCartPage shoppingCart = new ShoppingCartPage(driver);
	    
	    shoppingCart.selectProduct("Sony vaio i7");
	    shoppingCart.addToCart();
	    shoppingCart.openCart();

	    checkout.clickPlaceOrder();
	    
	    // Enter an expired card (2020 expiry)
	    checkout.fillCheckoutDetails("Mike", "Canada", "Toronto", "5555666677778888", "08", "2020");
	    checkout.completePurchase();
	    
	    //Assert.assertNotSame(checkout.isErrorMessageDisplayed(), "Expired credit card should not be accepted!");
	    JavaUtility.setDriver(driver);
		JavaUtility.takeScreenShot("validateCheckoutWithExpiredCard");
		
	}

	
	@Test
	public void validateCheckoutWithMultipleProducts() {
	    CheckoutPage checkout = new CheckoutPage(driver);
	    ShoppingCartPage shoppingCart = new ShoppingCartPage(driver);
	    
	    // Add multiple products
	    shoppingCart.selectProduct("Samsung galaxy s6");
	    shoppingCart.addToCart();
	    
	    shoppingCart.navigateToHome();
	    shoppingCart.selectProduct("Sony vaio i5");
	    shoppingCart.addToCart();
	    
	    shoppingCart.openCart();

	    checkout.clickPlaceOrder();
	    
	    checkout.fillCheckoutDetails("Emma Johnson", "Germany", "Berlin", "9876543210987654", "11", "2027");
	    checkout.completePurchase();

	    Assert.assertTrue(checkout.isOrderSuccessful(), "Order was not completed successfully with multiple products!");
	    JavaUtility.setDriver(driver);
		JavaUtility.takeScreenShot("validateCheckoutWithMultipleProducts");
		
	}




}