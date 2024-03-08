// Nama  : Muhammad Ryan Fikri Fakhrezi
// NIM   : L0122114
// Kelas : C

#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
#include <vector>
#include <list>
#include <stack>
#include <queue>
#include <set>
#include <map>
#include <ios>
#include <limits>
#include "rapidxml/rapidxml.hpp"

//class for the buyer's cart
class buyer{
    public:
        std::stack<std::string> cart;
};

//struct for the graph's edge list
struct similar{
    std::string book_a;
    std::string book_b;
    std::string similarity_type;
};

std::map<int, buyer*> all_buyer;
std::queue<int> buyer_queue;
std::set<std::string> all_book_ids;

class XMLDataHandler {
private:
    rapidxml::xml_document<> doc;
    rapidxml::xml_node<>* root_node = NULL;

public:
    XMLDataHandler() {
        // Perform XML parsing and store the DOM tree
        std::ifstream theFile("books.xml");
        std::vector<char> buffer((std::istreambuf_iterator<char>(theFile)), std::istreambuf_iterator<char>());
        buffer.push_back('\0');

        doc.parse<0>(&buffer[0]);
        root_node = doc.first_node("bookstore");
    }

    std::list<std::string> search(std::string keyword){
        std::list<std::string> found;   
        // Iterate over the book nodes
        for (rapidxml::xml_node<> * book_node = root_node->first_node("book");
                    book_node; 
                    book_node = book_node->next_sibling())
        {
        // Iterate over the book data
            for(rapidxml::xml_node<> * Book_metadata = book_node->first_node();
                    Book_metadata; 
                    Book_metadata = Book_metadata->next_sibling())
            {
                // Added the book's id to the search result whenever there's matched result
                if(Book_metadata->value()==keyword){
                    found.push_back(book_node->first_attribute("id")->value());
                }
            }
        }
        return found;
    }

    std::list<similar> generateGraphForSimilarities(){
        std::list<similar> similarity_list;

        for (rapidxml::xml_node<> * book_node_A = root_node->first_node("book");
            book_node_A; 
            book_node_A = book_node_A->next_sibling())
        {   
            for (rapidxml::xml_node<> * book_node_B = book_node_A->next_sibling();
                book_node_B; 
                book_node_B = book_node_B->next_sibling())
            {
                std::string Book_A_author = book_node_A->first_node("author")->value();
                std::string Book_A_genre = book_node_A->first_node("genre")->value();
                std::string Book_B_author = book_node_B->first_node("author")->value();
                std::string Book_B_genre = book_node_B->first_node("genre")->value();
                if(Book_A_author == Book_B_author){
                    similarity_list.push_back({book_node_A->first_attribute("id")->value(), book_node_B->first_attribute("id")->value(), "author"});
                    similarity_list.push_back({book_node_B->first_attribute("id")->value(), book_node_A->first_attribute("id")->value(), "author"});
                }
                if(Book_A_genre == Book_B_genre){
                    similarity_list.push_back({book_node_A->first_attribute("id")->value(), book_node_B->first_attribute("id")->value(), "genre"});
                    similarity_list.push_back({book_node_B->first_attribute("id")->value(), book_node_A->first_attribute("id")->value(), "genre"});
                }
            }
        }
        return similarity_list;
    }

    int getPrice(std::string id){
        // Iterate over the book nodes
        for (rapidxml::xml_node<> * book_node = root_node->first_node("book");
                    book_node; 
                    book_node = book_node->next_sibling())
        {
            // 
            if(book_node->first_attribute("id")->value() == id){
                return atoi(book_node->first_node("price")->value());
            }
        }
        return 0;
    }

    bool isThereSuchId(std::string id){
        // Iterate over the book nodes
        for (rapidxml::xml_node<> * book_node = root_node->first_node("book");
                    book_node; 
                    book_node = book_node->next_sibling())
        {
            // if there is ID match
            if(book_node->first_attribute("id")->value() == id){
                return true;
            }
        }
        return false;
    }

    void collectAllBookIds(){
        // Iterate over the book nodes
        for (rapidxml::xml_node<> * book_node = root_node->first_node("book");
                    book_node; 
                    book_node = book_node->next_sibling())
        {
            // if there is ID match, add to the set
            all_book_ids.insert(book_node->first_attribute("id")->value());
        }
    }

    void printBookDataBasedOfId(std::string id){ 
        // Iterate over the book nodes
        for (rapidxml::xml_node<> * book_node = root_node->first_node("book");
                    book_node; 
                    book_node = book_node->next_sibling())
        {
            // Iterate over the book data if the id is match
            if(book_node->first_attribute("id")->value()==id){
                std::cout << "| id     " << "= " << std::setw(100) << std::left << book_node->first_attribute("id")->value() << " |" << std::endl;
                for(rapidxml::xml_node<> * Book_metadata = book_node->first_node();
                        Book_metadata; 
                        Book_metadata = Book_metadata->next_sibling())
                {
                    // std::cout everything except the link
                    std::string name_book_metadata = Book_metadata->name();
                    if(name_book_metadata == "price"){
                        std::string value = Book_metadata->value();
                        std::string price = "IDR " + value + ",00";
                        std::cout << "| " << std::setw(7) << std::left << Book_metadata->name() << "= " << std::setw(100) << std::left << price << " |" << std::endl;
                    }
                    else if(name_book_metadata != "link"){
                        std::cout << "| " << std::setw(7) << std::left << Book_metadata->name() << "= " << std::setw(100) << std::left << Book_metadata->value() << " |" << std::endl;
                    }
                }
                std::cout << "| " << std::setw(111) << std::right << " |" << std::endl;
            }
        }
    }

    void printBook(std::string id){
        std::string link, name;
        // Iterate over the book nodes
        for (rapidxml::xml_node<> * book_node = root_node->first_node("book");
                    book_node; 
                    book_node = book_node->next_sibling())
        {
            // If the id is match, copy title's value and link's value
            if(book_node->first_attribute("id")->value()==id){
                name = book_node->first_node("title")->value();
                link = book_node->first_node("link")->value();
            }
        }
        std::string title = name + ".txt";
        std::ofstream outfile (title);
        outfile << "Pay to unlock this ebook, click the link below to proceeds to Google Play Books\n" << link << std::endl;
        outfile.close();
    }

    void printReceipt(std::stack<std::string> &Cart, int money)
    {
        std::stack<std::string> cart = Cart;
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
        while(cart.size()!=0) {
            for (rapidxml::xml_node<> * book_node = root_node->first_node("book");
                    book_node; 
                    book_node = book_node->next_sibling())
            {
                // print the book's data onto the .txt file if the id's match
                std::string current_book_id = book_node->first_attribute("id")->value();
                if(current_book_id == cart.top()){
                    outfile << book_node->first_node("title")->value() << '\n';
                    outfile << "\t\t  IDR " << book_node->first_node("price")->value() << ",00\n";
                    int price = atoi(book_node->first_node("price")->value());
                    total = total + price;
                    break;
                }
            }
            cart.pop();        
        }

        outfile << "\nTOTAL\t\t: IDR. " << total << ",00\n";
        outfile << "AMOUNT OF MONEY : IDR. " << money << ",00\n";
        outfile << "CHANGE\t\t: IDR. " << money-total << ",00\n";

        outfile << "======= THANK YOU FOR YOUR VISIT =======" << '\n';
        outfile.close();
        std::cout << "| The payment receipt has been saved on " << title << '\n';
    }
};

void clear_screen(){
    #ifdef _WIN32
        system("cls");
    #else
        system("clear");
    #endif
}

void WaitEnter() { 
    std::cout << "| Press Enter to Continue";
    std::cin.ignore(std::numeric_limits<std::streamsize>::max(),'\n');
}

std::stack<std::string> customer(){
    XMLDataHandler accessXml;
    std::stack<std::string> cart;
    std::list<similar> recommendation = accessXml.generateGraphForSimilarities();   

    //run search
    while(true){
        std::string find, choose;
        std::list<std::string> found;
        std::cout << "| ======================================== WELCOME TO FOOBAR BOOKSTORE ======================================== |" << std::endl;
        while(true){
            std::cout << "| enter the book's title/author/genre : "; getline(std::cin, find);
            found = accessXml.search(find);

            //not found
            if(found.size()!=0){break;}
            std::cout << "| Nothing found\n";
        }

        //found
        while(found.size()!=0){
            accessXml.printBookDataBasedOfId(*std::next(found.begin(), 0));
            found.pop_front();
        }

        //get it in the cart
        std::cout << "| Enter the product's ID that you want to buy\n| (input 's' to back to search or 'd' if you done with it): ";        
        do{
            std::cin >> choose;
            std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
            if (all_book_ids.count(choose) || choose == "s" || choose == "d")
                break;

            std::cout << "| The code's is not valid" << std::endl;
        }while (true);

        if(choose == "d"){
            return cart;
        }
        else if(choose == "s"){
            clear_screen();
            continue;
        }
        cart.push(choose);

        std::cout << "| " << choose << " already added to the cart\n";

        //Print the list of recommendation based of similarities
        std::cout << "| Recommendation:\n";
        for (const auto& similarBook : recommendation){
            if(choose.compare(similarBook.book_a)==0){
                if(similarBook.similarity_type.compare("author")==0){
                    std::cout << "| " << similarBook.book_b << " - " << "same author\n";
                }
                if(similarBook.similarity_type.compare("genre")==0){
                    std::cout << "| " << similarBook.book_b << " - " << "same genre\n";
                }
            }
        }
        WaitEnter();
        clear_screen();
    }
}

void processingPuchase(int queueNumber){
    XMLDataHandler accessXml;
    //Not recap
    buyer Buyer = *all_buyer[queueNumber];
    std::stack<std::string> Cart = Buyer.cart;
    
    //The cart is empty
    if(Cart.size()==0){
        std::cout << "| You didn't buy anything, what are you doing here?\n";
        delete all_buyer[queueNumber];
        return;
    }

    //Iterate over the cart
    int total = 0; int product = 1;
    while(Cart.size()!=0){
        accessXml.printBookDataBasedOfId(Cart.top());
        total += accessXml.getPrice(Cart.top());
        Cart.pop();
    }
    std::cout << "| The total price is IDR " << total << ",00\n";
    int money = 0;
    while(true){
        std::cout << "| How much is your money : "; std::cin >> money;
        if(money>=total){break;}
        std::cout << "| The money is not enough\n";
    }
    std::stack<std::string> Cart2 = Buyer.cart;
    while(Cart2.size()!=0){
        accessXml.printBook(Cart2.top());
        Cart2.pop();
    }
    accessXml.printReceipt(Buyer.cart, money);
}

int main(){
    int role;
    int people = 1;
    XMLDataHandler forCollectId;
    forCollectId.collectAllBookIds();
    while(true){
        clear_screen();
        bool canICloseTheStore = false;
        std::cout << "| === WELCOME TO BOOKSTORE SIMULATOR === |\n";
        std::cout << "| CHOOSE YOUR ROLE                       |\n";
        std::cout << "| 1] Customer                            |\n";
        std::cout << "| 2] Cashier                             |\n";
        std::cout << "| What's your role : "; 
        
        //check if the role is valid
        do{
            if (!(std::cin >> role))
                {
                    std::cout << "| Invalid input! Please enter an integer only." << '\n';
                    std::cin.clear();
                    std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
                    continue;
                }

            if ((role >= 1) && (role <= 2))
                break;

            std::cout << "| Please enter a valid number (1 or 2)" << std::endl;
        }while (true);

        std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
        switch(role){
            case 1:
            {
                std::cout << "| Now, you'll act like a customer who wants to buy books.\n";
                WaitEnter();
                clear_screen();
                buyer *Buyer = new buyer();
                Buyer->cart = customer();
                all_buyer[people] = Buyer;
                buyer_queue.push(people);
                ++people;
                break;
            }
            case 2:
            {
                XMLDataHandler accessXml;
                std::cout << "| Now, you will act like a cashier who will serve the customer's purchase.\n";
                WaitEnter();
                clear_screen();
                std::cout << "| ======================================== WELCOME TO FOOBAR BOOKSTORE ======================================== |" << std::endl;
                //RECAP
                if(buyer_queue.size()==0){
                    char confirm;
                    std::cout << std::setw(111) << std::left << "| There is no people in the line" << " |" << std::endl;
                    std::cout << "| Do you want to see the recap for today (y/n): "; std::cin >> confirm;
                    if(confirm=='y'){
                        for(const auto& it : all_buyer){
                            std::cout << "| Buyer #" << it.first << std::endl;
                            buyer *Buyer = all_buyer[it.first];
                            std::stack<std::string> Cart = Buyer->cart;
                            while(Cart.size()!=0){
                                accessXml.printBookDataBasedOfId(Cart.top());
                                Cart.pop();
                            }
                            delete Buyer;
                            std::cout << std::endl;
                        }
                    }
                    canICloseTheStore = true;
                    break;
                }
                
                processingPuchase(buyer_queue.front());
                WaitEnter();
                buyer_queue.pop();
                break;
            }
        }
        if(canICloseTheStore){
            char confirms='n';
            std::cout << "\n| Do you want to close the store (y/n) : ";std::cin >> confirms;
            while(confirms!='y'){
                std::cout << "\n| Please close, i want to sleep\n(y/n) : ";
                std::cin >> confirms;
            }
            std::cout << "| Thank you";
            break;
        }
    }
}