/* 
 Program ED1 - List Encad Dulp 01/09/18
 Vitor Monteiro de Novaes Menezes
 Estrutura da Dados 1 - UECE  
 */

#include <iostream>
#include <string>
#include <cstdio>
#include <cstdlib>

using namespace std;

class Cell{
    private:
        int value;
        Cell* next;
        Cell* previous;
    public:
        Cell(int value){
            this->SetNext(NULL);
            this->SetPrevious(NULL);
            this->SetValue(value);
        }
        ~Cell(){}
        void SetValue(int value){    
            this->value = value;
        }
        void SetNext(Cell *li){
            this->next = li;
        }
        void SetPrevious(Cell *li){
            this->previous = li;
        }
        int GetValue(){
            return this->value;
        }
        Cell* GetNext(){
            return this->next;
        }
        Cell* GetPrevious(){
            return this->previous;
        }
};

class Stack{
    private:
        Cell* init;
        Cell* last; 
    public:

        // Stack Methods -------------------------------------------
        Stack(){
            this->init = NULL;
            this->last = NULL;
        }
        void SetInit(Cell* li){
            this->init = li;
        }    
        void SetLast(Cell* li){
            this->last = li;
        }
        Cell* GetInit(){
            return this->init;
        }
        Cell* GetLast(){
            return this->last;
        }

        // Stack Methods validates ----------------------------------
         bool Empty(){
            return (this->GetInit() == NULL);
        }             

        // Insert Stack Methods -------------------------------------
        void PushStack(int value){
            if (this->Empty()){
                this->Push_StackEmpty(value);
            }else{
                this->Push_StackNotEmpty(value);
            }
        }

        void Push_StackEmpty(int value){
            init = new Cell(value);
            this->SetLast(init);
        }

        void Push_StackNotEmpty(int value){
            Cell* aux_cell = new Cell(value);
            this->GetLast()->SetPrevious(aux_cell);
            aux_cell->SetNext(this->GetLast());
            this->SetLast(aux_cell);
        }

        //Remove Stack Methods --------------------------------------
        
        void PopStack() {
            Cell *last_element = this->GetLast();
            
            if(this->Empty()){
                 cout << "=> Stack Empty !!" << endl << endl;
            
            }else if(last_element->GetNext() != NULL){
                
                this->SetLast(last_element->GetNext());
                this->GetLast()->SetPrevious(NULL);
                last_element->SetNext(NULL);
                delete last_element;

            }else if(last_element->GetNext() == NULL){
                this->SetLast(NULL);
                this->SetInit(NULL);
                delete last_element;
            }

        }

        void DestroyStack(){
            while(this->GetInit() != NULL){
                this->PopStack();
            }
        }

        void CountElementsStack(){
            int count = 0;
            Cell     *aux = this->GetInit();
            
            while(aux){
                count++;
                aux = aux->GetPrevious();
            }
            cout << "=> Size : " << count << endl;
        
        }


        // Search Stack Methods -------------------------------------
        
        bool Search(int value){
            Stack aux;
            Cell* aux_cell = this->GetLast();
            bool found = false;

            if(this->Empty()){
                cout << endl << "=> Row Empty !!";
                return found;
            }else{
                while(value != aux_cell->GetValue()){                                                                        
                    if(aux_cell->GetNext() == NULL ){
                      break;
                    }else{
                        aux.PushStack(aux_cell->GetValue());
                        this->PopStack();
                        aux_cell = this->GetLast();
                    }   

                }
                
                if(value == aux_cell->GetValue()){
                    found = true;
                }

                if(!aux.Empty()){
                    aux_cell = aux.GetLast();
                    while(aux.GetLast() != NULL){
                        this->PushStack(aux_cell->GetValue());
                        aux.PopStack();
                        aux_cell = aux.GetLast();
                    }
                }

                return found;
            }
        }
        // Print Stack Methods --------------------------------------
        
        void PrintStack(bool *search, int choice){      
            Cell* aux_cell = this->GetLast(); 

            this->CountElementsStack();
            if(this->Empty()){
                cout << "=> Stack Empty !!" << endl;
            }else{
                cout << "=> The Stack : " << endl;
            }

            if(choice == 4 and *search == false){
                cout << "=> There is not the element !" << endl;
            }else if(choice == 4 and *search == true){
                *search = false;
                cout << "=> There is the element !" << endl;
            }

            cout << endl;
            while(aux_cell){
                cout << "\t|" << aux_cell->GetValue() << "|" << endl;
                aux_cell = aux_cell->GetNext();
            }
            cout << endl << endl;
        }
};


int main(){
    Stack stack;
    int value,choice = 0;
    bool search = false;
    system("clear");
    
    cout << "Program Stack, OO, LIFO (Last in, First out)" << endl;
    cout << "ED1" << endl << endl;
    cout << endl << "=> Enter any to continue . . . " << endl;
    getchar();

    while(true){
        system("clear");
        cout << endl << "Program : Struct Stack " << endl << 
        "----------------------------------- " << endl;  
        stack.PrintStack(&search,choice);
        cout << "1 - Insert element in Stack (Verified)" << endl;
        cout << "2 - Remove element in Stack (Verified)" << endl;
        cout << "3 - Destroy elements from Stack (Verified)" << endl;
        cout << "4 - Search by value" << endl;
        cout << "0 - Exit" << endl;
        cin >> choice;

        switch(choice){
            case 1 :
                cout << "=> Enter the number for cell insert last position: ";
                cin >> value;
                stack.PushStack(value);
            break;
            case 2 :
                stack.PopStack();
            break;
            case 3 :
                char sure;
                cout << "=> Are you sure ? (y/n): ";
                cin >> sure;
                if(sure == 'y'){
                    stack.DestroyStack();
                }else{
                    continue;
                }
            break;
            case 4 :
                cout << "=> Enter the value for search: ";
                cin >> value;
                search = stack.Search(value);
            break;
            case 0 : exit(0);break;
            default:;
        }        
    }



    return 0;
}