#include "XMLDataHandler.h"

std::list<std::string> XMLDataHandler::search(std::string keyword){
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

void XMLDataHandler::printBookDataBasedOfId(std::string id){ 
    // Iterate over the book nodes
    for (rapidxml::xml_node<> * book_node = root_node->first_node("book");
                book_node; 
                book_node = book_node->next_sibling())
    {
        // Iterate over the book data if the id is match
        if(book_node->first_attribute("id")->value()==id){
            std::cout << "id\t= " << book_node->first_attribute("id")->value() << std::endl;
            for(rapidxml::xml_node<> * Book_metadata = book_node->first_node();
                    Book_metadata; 
                    Book_metadata = Book_metadata->next_sibling())
            {
                // std::cout everything except the link
                std::string name_book_metadata = Book_metadata->name();
                if(name_book_metadata == "price"){
                    std::cout << Book_metadata->name() << "\t= " << "IDR " << Book_metadata->value() << ",00" << std::endl;
                }
                else if(name_book_metadata != "link"){
                    std::cout << Book_metadata->name() << "\t= " << Book_metadata->value() << std::endl;
                }
            }
            std::cout << std::endl;
        }
    }
}

void XMLDataHandler::printBook(std::string id){
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

void XMLDataHandler::print_struck(std::stack<std::string> &Cart)
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
                outfile << "IDR " << book_node->first_node("price")->value() << ",00\n";
                int price = atoi(book_node->first_node("price")->value());
                total = total + price;
                break;
            }
        }
        cart.pop();        
    }

    outfile << "\nTOTAL\t\t: IDR. " << total << ",00\n";
    //outfile << "AMOUNT OF MONEY : IDR. " << ShoppingCart::show_balance() + total << '\n';
    //outfile << "CHANGE\t\t: IDR. " << ShoppingCart::show_balance() << '\n';

    outfile << "======= THANK YOU FOR YOUR VISIT =======" << '\n';
    outfile.close();
    std::cout << "The payment receipt has been saved on " << title << '\n';
}

std::list<similar> XMLDataHandler::graph_generation(){
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