/*
Nama : Muhammad Ryan Fikri Fakhrezi
NIM : L0122114
Kelas : C
*/

#include <iostream>
#include <iterator>
#include <fstream>
#include <map>
#include <string>
#include <ios>
#include <limits>
#include <iomanip>
#include <ctime>

class ProductItem{
    public:
        std::string name;
        int stock;
        int price;
};

class ShoppingCart{
    private:
        static int money;
    public:
        void subtract_balance(int price)
        {
            money-=price;
        }
        static int show_balance()
        {
            return money;
        }
        static void get_money(int in)
        {
            money+=in;
        }
        std::string name;
        int price;
        int qty;
};

std::map<std::string, ProductItem> code_product; 
std::map<std::string, ShoppingCart> code_product_in_cart; 
int ShoppingCart::money = 0;

void clear_screen(){
    #ifdef _WIN32
        system("cls");
    #else
        system("clear");
    #endif
}

void WaitEnter() { 
    std::cout << "Press Enter to Continue";
    std::cin.ignore(std::numeric_limits<std::streamsize>::max(),'\n');
    std::cin.ignore();
}

void product_list()
{
    std::cout << "List of product available:" << '\n';
    std::cout << "Code\tname\t\t\t\tstock\tprice" << '\n';
    for (const auto& it : code_product) {
        std::cout << std::setw(7) << std::left << it.first << "\t" << std::setw(31) << std::left <<it.second.name << "\t"  << it.second.stock << "\t"  << it.second.price << '\n';
    }
}

void shopping_cart()
{
    std::cout << "List of product inside the shopping cart:" << '\n';
    std::cout << "Code\tname\t\t\t\tQTY\tprice" << '\n';
    for (const auto& it : code_product_in_cart) {
        std::cout << std::setw(7) << std::left << it.first << "\t" << std::setw(31) << std::left << it.second.name << "\t"  << it.second.qty << "\t"  << it.second.price << '\n';
    }
}

void stocking(){
    int items;
    std::cout << "total of new ProductItem : "; std::cin >> items;
    for(int i=0; i<items; i++){
        clear_screen();
        product_list();
        puts("");
        std::string code;
        std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
        ProductItem* product = new ProductItem();
        std::cout << "Product's name\t: "; std::getline(std::cin, product->name);
        std::cout << "Product's stock\t: "; std::cin >> product->stock;
        std::cout << "Product's price\t: "; std::cin >> product->price;
        while(true){
            std::cout << "Product's code\t: "; std::cin >> code;
            if(code_product.count(code) !=1 ){
                code_product[code] = *product;
                break;
            } else{
                char yn;
                std::cout << "the code already been used\nDo you want to overwrite the data (y/n): "; std::cin>>yn;
                if(yn=='y'){
                    code_product[code] = *product; 
                    break;
                }
                else if(yn=='n'){
                    continue;
                }
            }
        }
        puts("");
    }
}

void sell(const std::string& code){
    if(code_product.count(code) == 1){
        ProductItem &product = code_product[code];
        int qty = 0;
        std::cout << "qty: "; std::cin >> qty;
        if(qty > product.stock || ShoppingCart::show_balance()<qty*product.price){
            std::cout << "either the qty is out of the stock or you're bokek" << '\n';
            WaitEnter();
            return;
        }
        if(code_product_in_cart.count(code)!=1){
            ShoppingCart *cart = new ShoppingCart();
            cart->name = product.name;
            cart->price = product.price;
            cart->qty = qty; product.stock-=qty;
            cart->subtract_balance(cart->price*qty);
            code_product_in_cart[code] = *cart;
        }
        else if(code_product_in_cart.count(code)==1)
        {
            ShoppingCart &cart = code_product_in_cart[code];
            cart.qty+=qty; product.stock-=qty;
            cart.subtract_balance(cart.price*qty);
        }
    }
    else{
        std::cout << "There is no such item's code" << '\n';
    }
}

void print_struck()
{
    time_t now = time(0); tm *ltm = localtime(&now);
    std::string date = std::to_string(ltm->tm_mday) + "-" + std::to_string(1 + ltm->tm_mon) + "-" +  std::to_string(1900 + ltm->tm_year);
    std::string clock = std::to_string(ltm->tm_hour) + "." + std::to_string(ltm->tm_min);
    std::string title = date + "_" + clock +".txt";
    std::ofstream outfile (title);
    outfile << "=========== PAYMENT RECEIPT ============" << '\n';
    outfile << "----------------------------------------" << '\n';
    outfile << "Date  : " << ltm->tm_mday << "/" << 1 + ltm->tm_mon << "/" << 1900 + ltm->tm_year << '\n';
    outfile << "Clock : " << ltm->tm_hour << ":" << ltm->tm_min << '\n';
    outfile << "----------------------------------------" << '\n';
    static int total = 0;
    for (const auto& it : code_product_in_cart) {
    outfile << std::setw(7) << std::left << it.first << "\t" << std::setw(23) << std::left << it.second.name << '\n';
    outfile << " " << it.second.qty << "PCS x\t" << it.second.price << "\t= IDR. " << it.second.qty * it.second.price << '\n';
    total+=it.second.qty*it.second.price;
    }
    outfile << "\nTOTAL\t\t: IDR. " << total << '\n';
    outfile << "AMOUNT OF MONEY : IDR. " << ShoppingCart::show_balance() + total << '\n';
    outfile << "CHANGE\t\t: IDR. " << ShoppingCart::show_balance() << '\n';

    outfile << "======= THANK YOU FOR YOUR VISIT =======" << '\n';
    outfile.close();
    std::cout << "The payment receipt has been saved on " << title << '\n';
}

void pay()
{
    time_t now = time(0); tm *ltm = localtime(&now);
    std::string date = std::to_string(ltm->tm_mday) + "/" + std::to_string(1 + ltm->tm_mon) + "/" +  std::to_string(1900 + ltm->tm_year);
    std::cout << "=========== PAYMENT RECEIPT ============" << '\n';
    std::cout << "----------------------------------------" << '\n';
    std::cout << "Date  : " << date << '\n';
    std::cout << "Clock : " << ltm->tm_hour << ":" << ltm->tm_min << '\n';
    std::cout << "----------------------------------------" << '\n';
    static int total = 0;
    for (const auto& it : code_product_in_cart)   {
        std::cout << std::setw(7) << std::left << it.first << "\t" << std::setw(23) << std::left << it.second.name << '\n';
        std::cout << " " << it.second.qty << "PCS x\t" << it.second.price << "\t= IDR. " << it.second.qty * it.second.price << '\n';
        total+=it.second.qty*it.second.price;
    }
    std::cout << "\nTOTAL\t\t: IDR. " << total << '\n';
    std::cout << "AMOUNT OF MONEY : IDR. " << ShoppingCart::show_balance() + total << '\n';
    std::cout << "CHANGE\t\t: IDR. " << ShoppingCart::show_balance() << '\n';

    std::cout << "======= THANK YOU FOR YOUR VISIT =======" << '\n';
    print_struck();
}

void customers(){
    int money;
    std::cout << "How much your money: "; std::cin >> money; ShoppingCart::get_money(money);
    while(true){
        clear_screen();
        std::string item;
        std::cout << "balance available: " << ShoppingCart::show_balance() << '\n';
        product_list();
        puts("");
        shopping_cart();
        puts("");
        std::cout << "Enter the item's code that you want to buy (enter 'done' to go to cashier): "; std::cin >> item;
        if(item=="done"){
            break;
        }else{
            sell(item);
        }
    }
    pay();
}

void initialize(){
    ProductItem* productA=new ProductItem();
    productA->name="SEDAAP SALERO PADANG 86";
    productA->price=3200;
    productA->stock=10;
    code_product["S01E01"]=*productA;

    ProductItem* productB=new ProductItem();
    productB->name="GARNIER MEN ACNO FIGHT WASABI";
    productB->price=36700;
    productB->stock=10;
    code_product["S02E01"]=*productB;

    ProductItem* productC=new ProductItem();
    productC->name="LIFEBUOY BW LEMON FRESH 90";
    productC->price=5000;
    productC->stock=10;
    code_product["S02E02"]=*productC;

    ProductItem* productD=new ProductItem();
    productD->name="INDOMIE GORENG ACEH 90";
    productD->price=2820;
    productD->stock=10;
    code_product["S01E02"]=*productD;
}

int main()  {
    int role; char yn;
    initialize();
    while(true){
        std::cout << "1. Customer\n2. Cashier\nWho are you: "; std::cin >> role;
        switch(role){
            case 1:
            {
                code_product_in_cart.clear();
                ShoppingCart cart;
                cart.subtract_balance(cart.show_balance()); 
                customers();
                break;
            }
            case 2:
            {
                stocking();
                break;
            }
        }
        while(yn!='y' || yn!='n'){
            std::cout << "do you wish to reenrole (y/n): "; std::cin >> yn;
            if(yn=='y' || yn=='n'){break;}
            else{std::cout<<"the choice is not valid\n";continue;}
        }
        if(yn=='n'){break; clear_screen();}
    }
}