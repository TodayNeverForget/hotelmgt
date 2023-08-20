package com.yp.hotelmgt.view;

import com.yp.hotelmgt.domain.Bill;
import com.yp.hotelmgt.domain.DiningTable;
import com.yp.hotelmgt.domain.Employee;
import com.yp.hotelmgt.domain.Menu;
import com.yp.hotelmgt.service.*;
import com.yp.hotelmgt.utils.Utility;

import java.util.List;

/**
 * @author 杨鹏
 * @version 1.0
 */
public class hotelMenu {
    char input;

    public static void main(String[] args) {
        new hotelMenu().mainMen();
    }

    //主菜单
    public void mainMen() {
        EmployeeService employeeService = new EmployeeService();

        boolean mainMenLoop = true;
        while (mainMenLoop) {
            System.out.println("---------------欢迎登陆杨氏酒店管理系统---------------");
            System.out.println("\t\t1 登陆酒店");
            System.out.println("\t\t9 退出酒店");

            System.out.print("请输入：");
            input = Utility.readChar();
            switch (input) {
                case '1':
                    System.out.print("账 号：");
                    String empId = Utility.readString(20);
                    System.out.print("密 码：");
                    String pwd = Utility.readString(32);
                    Employee emp;
                    if ((emp = employeeService.login(empId, pwd)) != null) {
                        secMen(emp);
                    } else {
                        System.out.println("登陆失败！");
                    }
                    break;
                case '9':
                    System.out.println("退出酒店系统...");
                    mainMenLoop = false;
                    break;
            }

        }

    }


    //二级菜单
    public void secMen(Employee emp) {
        DiningTableService diningTableService = new DiningTableService();
        MenuService menuService = new MenuService();
        Order order = new Order();
        BillService billService = new BillService();

        System.out.println("---------------欢迎进入杨氏酒店管理系统---------------");
        System.out.println("姓名：" + emp.getName() + " 工号：" + emp.getEmpId());
        System.out.println("\t\t1 显示餐桌状态");
        System.out.println("\t\t2 预定    餐桌");
        System.out.println("\t\t3 显示所有菜品");
        System.out.println("\t\t4 点餐    服务");
        System.out.println("\t\t5 查看    账单");
        System.out.println("\t\t6 结账");
        System.out.println("\t\t9 退出登陆");

        boolean secMenLoop = true;
        while (secMenLoop) {
            System.out.print("------[主菜单]------\n请输入：");
            input = Utility.readChar();

            switch (input) {
                case '1':
                    System.out.println("-------餐桌状态-------");
                    System.out.println("桌号\t\t\t状态");
                    showDiningTableState(diningTableService.diningTablesState());
                    System.out.println("-------显示结束-------");
                    break;
                case '2':
                    System.out.println("-------餐桌预定-------");
                    System.out.print("请输入餐桌号(-1退出)：");
                    Integer id = Utility.readInt();
                    if (id == -1) break;
                    if (diningTableService.diningTable(id) == null) {
                        System.out.println("餐桌号不存在！");
                    } else if (!diningTableService.diningTable(id).getState().equals("空")) {
                        System.out.println("餐桌占用中...");
                    } else {
                        char confirmSelection = Utility.readConfirmSelection();
                        if (confirmSelection == 'N') {
                            System.out.println("已取消！");
                            break;
                        }
                        System.out.print("预定人姓名：");
                        String orderName = Utility.readString(50);
                        System.out.print("预定人电话：");
                        String orderTel = Utility.readString(20);
                        if (diningTableService.diningTableOrder(id, orderName, orderTel)) {
                            System.out.println("预定成功！");
                        } else {
                            System.out.println("预定失败！");
                        }
                    }
                    break;
                case '3':
                    System.out.println("-------菜单显示-------");
                    System.out.println("\t名称\t类型\t\t价格");
                    showMenus(menuService.menus());
                    System.out.println("-------显示结束-------");
                    break;
                case '4':
                    System.out.println("-------点餐服务-------");
                    System.out.print("点餐桌号(-1退出)：");
                    Integer diningTableId = Utility.readInt();
                    //验证餐桌
                    if (diningTableId == -1) {
                        System.out.println("已取消！");
                        break;
                    } else if (diningTableService.diningTable(diningTableId) == null) {
                        System.out.println("桌号不存在！");
                        break;
                    } else if (diningTableService.diningTable(diningTableId).getState().equals("空")) {
                        System.out.println("请先预定该桌号！");
                        break;
                    }
                    System.out.print("菜品(-1退出)；");
                    String name = Utility.readString(10);
                    //验证菜品
                    if (name.equals("-1")) {
                        System.out.println("已取消！");
                        break;
                    } else if (menuService.menu(name) == null) {
                        System.out.println("菜品不存在！");
                        break;
                    }
                    System.out.print("菜品数量(-1退出)：");
                    int nums = Utility.readInt();
                    if (nums == -1) {
                        System.out.println("已取消！");
                        break;
                    }
                    char confirmSelection = Utility.readConfirmSelection();
                    if (confirmSelection == 'N') break;
                    if (order.foodOrder(diningTableId, name, Math.abs(nums))) {
                        System.out.println("-------点餐成功-------");
                    } else {
                        System.out.println("-------点餐失败-------");
                    }
                    break;
                case '5':
                    System.out.println("-------显示账单信息-------");
                    System.out.println("编号\t\t菜品号\t菜品量\t金额\t桌号\t日期\t\t\t\t\t\t状态");
                    showBills(billService.bills());
                    System.out.println("-------显示    结束-------");
                    break;
                case '6':
                    System.out.println("-------结    账-------");
                    System.out.print("输入结账桌号：");
                    int checkOutDiningTableId = Utility.readInt();
                    //验证餐桌
                    if (checkOutDiningTableId == -1) {
                        System.out.println("已取消！");
                        break;
                    } else if (diningTableService.diningTable(checkOutDiningTableId) == null) {
                        System.out.println("桌号不存在！");
                        break;
                    } else if (billService.checkBill(checkOutDiningTableId).size() == 0) {
                        System.out.println("该桌号无需结账！");
                        break;
                    }
                    System.out.println("待支付金额 " + checkAllBill(billService.checkBill(checkOutDiningTableId)));
                    System.out.print("结账方式(现金/微信/支付宝) 回车以退出：");
                    String state = paymentType();
                    if (state.equals("")) {
                        System.out.println("取消结算！");
                        break;
                    }
                    if (Utility.readConfirmSelection() == 'N') {
                        System.out.println("取消结算！");
                        break;
                    }
                    order.checkOut(checkOutDiningTableId, state);
                    System.out.println("-------结账完成-------");
                    break;
                case '9':
                    System.out.println("退出");
                    secMenLoop = false;
                    break;
                default:
                    System.out.println("请重新输入");

            }

        }


    }

    //显示餐桌状态
    public void showDiningTableState(List<DiningTable> diningTableStates) {

        for (DiningTable diningTableState : diningTableStates) {
            System.out.println(diningTableState.getId() + "\t\t\t" + diningTableState.getState());
        }

    }

    //显示菜品
    public void showMenus(List<Menu> Menus) {

        for (Menu menu : Menus) {
            System.out.println(menu);
        }

    }

    //显示账单
    public void showBills(List<Bill> bills) {

        for (Bill bill : bills) {
            System.out.println(bill);
        }

    }

    //计算订单总额
    public double checkAllBill(List<Bill> bills) {
        double totle = 0;

        for (Bill bill : bills) {
            totle += bill.getMoney();
        }

        return totle;
    }

    //校验支付方式
    public String paymentType() {

        while (true) {
            String type = Utility.readString(10);

            switch (type) {
                case "现金":
                case "微信":
                case "支付宝":
                    return type;
                default:
                    System.out.print("输入有误，重新输入：");
                    break;
            }
        }

    }
























































}




















