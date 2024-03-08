#include "XMLDataHandler.h"

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
}

class buyer{
    public:
        std::stack<std::string> cart;
};

std::map<int, buyer*> all_buyer;
std::queue<int> buyer_queue;

std::stack<std::string> customer(){
    XMLDataHandler accessXml;
    std::stack<std::string> cart;
    std::string find, choose;
    std::list<std::string> found;
    std::list<similar> recommendation = accessXml.graph_generation();   

    //run search
    while(true){
        std::cout << "WELCOME TO FOOBAR BOOKSTORE" << std::endl;
        while(true){
            std::cout << "enter book's title/author/genre : "; getline(std::cin, find);
            found = accessXml.search(find);

            //not found
            if(found.size()!=0){break;}
            std::cout << "Nothing found\n";
        }

        //found
        while(found.size()!=0){
            accessXml.printBookDataBasedOfId(*std::next(found.begin(), 0));
            found.pop_front();
        }

        //get it in the cart
        std::cout << "Enter the product's ID that you want to buy\n(press 's' to back to search or 'd' if you done with it): "; std::cin >> choose;
        std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
        
        if(choose.compare("s")!=0 && choose.compare("d")!=0){
            cart.push(choose);
            std::cout << choose << " already added to the cart\n";

        }
        else if(choose.compare("d")==0){
            return cart;
        }

        //gets recommendation from graph
        std::cout << "Recommendation:\n";
        for (const auto& similarBook : recommendation){
            if(choose.compare(similarBook.book_a)==0){
                if(similarBook.similarity_type.compare("author")==0){
                    std::cout << similarBook.book_b << " - " << "same author\n";
                }
                if(similarBook.similarity_type.compare("genre")==0){
                    std::cout << similarBook.book_b << " - " << "same genre\n";
                }
            }
        }
        WaitEnter();
        clear_screen();
    }
}

int main(){
    std::cout << "Welcome\n";
    int role;
    int people = 1;
    while(true){
        bool canICloseTheStore = false;
        XMLDataHandler accessXml;
        std::cout << "1. Customer\n2. Cashier\nWho are you: "; std::cin >> role; std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
        switch(role){
            case 1:
            {
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
                clear_screen();
                std::cout << "you're cashier\n";
                if(buyer_queue.size()==0){
                    char confirm;
                    std::cout << "There is no people in the line" << std::endl;
                    std::cout << "Do you want to see the recap for today (y/n): "; std::cin >> confirm;
                    if(confirm=='y'){
                        for(const auto& it : all_buyer){
                            std::cout << "Buyer #" << it.first << std::endl;
                            buyer Buyer = *all_buyer[it.first];
                            std::stack<std::string> Cart = Buyer.cart;
                            while(Cart.size()!=0){
                                accessXml.printBookDataBasedOfId(Cart.top());
                                Cart.pop();
                            }
                            delete all_buyer[it.first];
                            std::cout << std::endl;
                        }
                    }
                    canICloseTheStore = true;
                    break;
                }
                buyer Buyer = *all_buyer[buyer_queue.front()];
                std::stack<std::string> Cart = Buyer.cart;
                while(Cart.size()!=0){
                    accessXml.printBookDataBasedOfId(Cart.top());
                    accessXml.printBook(Cart.top());
                    Cart.pop();
                }
                accessXml.print_struck(Buyer.cart);
                buyer_queue.pop();
                WaitEnter();
                break;
            }
        }
        if(canICloseTheStore){
            char confirms='n';
            std::cout << "\nDo you want to close the store (y/n) : ";std::cin >> confirms;
            while(confirms!='y'){
                std::cout << "\nPlease close, i want to sleep\n(y/n) : ";
                std::cin >> confirms;
            }
            std::cout << "Thank you";
            break;
        }
    }
}