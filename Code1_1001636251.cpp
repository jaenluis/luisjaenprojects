//Luis Jaen ID: 1001636251

#include <iostream>
#include <string>

#define price 60 //pencil price

//the statuses are held here so i can use the words for readability and the numbers they stand for in my nested switch cases in main()
enum info
{
    ExactChange,
    GiveBackChange,
    InsufficientPayment,
    InsufficientChange,  
};

bool buyPencils(int userPen, int userMon, int& inv, int& cha, int& status)
{
    int totalNeeded = userPen*price;
    int tempChange = userMon - userPen*price;

    if(userPen <= inv && userMon == totalNeeded) //exact change
    {
        inv -= userPen;
        cha += userMon;
        status = info::ExactChange;
        return true;
    }
    else if(userPen <= inv && userMon > totalNeeded && userMon <=cha) //give change back
    {
        inv -= userPen;
        cha += userMon;
        cha -= tempChange;
        status = info::GiveBackChange;
        return true;
    }
    else
    {
        if(userMon < totalNeeded) //not enough money from user for total needed
        {
            status = info::InsufficientPayment;
        }
        else if(userMon > cha) //not enough change in machine
        {
            status = info::InsufficientChange;
        }
        return false;
    }   
}

//returns a string of the money in $xx.yy form
std::string displayMoney (int cash)
{
    std::string dollars{std::to_string(cash/100)};
    std::string cents{std::to_string(cash%100)};
    return + "$" + dollars + "." + (cents.size() == 1 ? "0" : "") + cents;
}

//prints menu and gets the choice
int menu()
{
    int choice;

    std::cout << "\nPlease choose from the following options\n\n" << std::endl;
    std::cout << "0. No Pencils for me today" << std::endl;
    std::cout << "1. Purchase pencils" << std::endl;
    std::cout << "2. Check inventory level" << std::endl;
    std::cout << "3. Check change level" << std::endl;
    std::cout << "\nChoice : ";
    std::cin >> choice;

    while(std::cin.fail()) //check for letter
    {
        std::cin.clear();
        std::cin.ignore(1000,'\n');
        std::cout << "\nInput must be numeric. Please reenter ";
        std::cin >> choice;
    }
    return choice;
}

int main()
{
    int inventory{100};
    int change{500};
    int userP; //user pencils
    int userM; //user money
    int stat;

    bool x{true};

    std::cout << "Welcome to my Pencil Machine" << std::endl;

    while(x)
    {
        switch(menu())
        {
            case 0: x = false; break;
            case 1: 
                    if(inventory!=0) //if there is inventory to sell, allow user to buy
                    {
                        std::cout << "\nA pencil costs " << displayMoney(price) << std::endl << std::endl << "How many pencils would you like to purchase? ";
                        std::cin >> userP;
                        while(std::cin.fail()) //check for letter
                        {
                            std::cin.clear();
                            std::cin.ignore(1000,'\n');
                            std::cout << "\nInput must be numeric. Please reenter ";
                            std::cin >> userP;
                        }  
                    }
                    else //there is no inventory
                    {
                        std::cout << "\nThe Pencil Dispenser is out of inventory." << std::endl;
                        break;
                    } 

                    if(userP<=0)
                    {
                        std::cout << "\nYou must purchase at least one pencil." << std::endl;
                    }
                    else if(userP>inventory)
                    {
                        std::cout << "\nThe machine does not have that many pencils - buy " << inventory << " or fewer." << std::endl;
                    }
                    else //inputs pencil amount wanted that is within range of pencils available
                    {
                        std::cout << "\nYour total is " << displayMoney(userP*price) << std::endl;
                        std::cout << "\nEnter your payment (in cents) ";
                        std::cin >> userM;

                        while(std::cin.fail()) //check for letter
                        {
                            std::cin.clear();
                            std::cin.ignore(1000,'\n');
                            std::cout << "\nInput must be numeric. Please reenter ";
                            std::cin >> userM;
                        }
                        

                        if(buyPencils(userP, userM, inventory, change, stat)) //if this is true, then...
                        {
                            switch(stat) //transaction completed statuses
                            {
                                case ExactChange: std::cout << "\nHere's your pencils. Thank you for exact payment" << std::endl; break;
                                case GiveBackChange: std::cout << "\nHere is your pencils and your change of " << displayMoney(userM - (userP*price)) << std::endl; break;
                            }
                        }
                        else
                        {
                            switch(stat) //transaction failed statuses
                            {
                                case InsufficientPayment: std::cout << "\nYou did not enter a sufficient payment. No pencil for you." << std::endl; break;
                                case InsufficientChange: std::cout << "\nThis Pencil Dispenser does not have enough change and cannot accept your payment." << std::endl; break;
                            } 
                        }
                    }
                    break;
            case 2: std::cout << "\nThe current inventory level is " << inventory << std::endl;
                    break;
            case 3: std::cout << "\nThe current change level is " << change << std::endl; 
                    break;
            default: std::cout << "\nInvalid menu choice. Please choose again." << std::endl;
        }

    }
    return 0;
}