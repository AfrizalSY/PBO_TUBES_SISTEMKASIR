
import Controller.CashierController;
import Model.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author krobus
 */
public class Main {

    public static void main(String[] args) {
        CashierController controller = new CashierController();

        Item menu1 = new Beverage("Amer", 50000, 1000);
        Item menu2 = new Beverage("Latte", 30000, 200);

        Item menu3 = new Food("Babi kecap", 80000, 10);
        Item menu4 = new Food("Sate Babi", 50000, 10);

        Order order1 = controller.createNewOrder(1);
        Order order2 = controller.createNewOrder(2);

        controller.addItem(menu1, 10, order1);
        controller.addItem(menu3, 1, order1);
        System.out.println(controller.calculateOrderPrice(order1));

    }
}
