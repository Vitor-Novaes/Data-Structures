/* 
 Program ED1 - List Encad Simple 30/09/18
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
    public:
        Cell(int value){
            this->SetNext(NULL);
            this->SetValue(value);
        }
        ~Cell(){}
        void SetValue(int value){
            this->value = value;
        }
        void SetNext(Cell *li){
            this->next = li;
        }
        int GetValue(){
            return this->value;
        }
        Cell* GetNext(){
            return this->next;
        }
};

class List{
    private:
        Cell* init;
        Cell* last;
        int size;
    public:

        // List Methods -------------------------------------------
        List(){
            this->SetInit(NULL);
            this->SetLast(NULL);
            this->SetSize(0);
        }
        void SetInit(Cell* li){
            this->init = li;
        }    
        void SetLast(Cell* li){
            this->last = li;
        }
        void SetSize(int size){
            this->size = size;
        }
        int GetSize(){
            return this->size;
        }
        Cell* GetInit(){
            return this->init;
        }
        Cell* GetLast(){
            return this->last;
        }
        
        // Empty List Methods --------------------------------------
        bool Empty(){
            return (this->GetInit() == NULL);
        }

        bool OneElement(){
            return(this->GetSize() == 1);
        }


        // Insert List Methods -------------------------------------
        
        void Insert(int value){
            if (this->GetInit() == NULL){
                this->InsertCell_ListEmpty(value);

            }else{
                this->InsertCell_End(value);
            }
        }
        void InsertCell_ListEmpty(int value){
            init = new Cell(value);
            this->SetLast(init);
            this->SetSize(this->GetSize() + 1);
        }
        void InsertCell_End(int value){
            Cell* aux_cell = new Cell(value);
            aux_cell->SetNext(last);
            this->SetLast(aux_cell);
            this->SetSize(this->GetSize() + 1);
        }

        void InsertCell_Init(int value){
            Cell* aux_cell = new Cell(value);
            Cell* aux_init = this->GetInit();

            aux_init -> SetNext(aux_cell);
            this->SetInit(aux_cell);
            this->SetSize(this->GetSize() + 1);
        }

        void InsertCell_Middle(int value){
            Cell* aux_cell = new Cell(value);
            Cell* aux_middle = this->GetLast();
            int size = this->GetSize();
            int middle = size/2;

            while(middle > 0.5){
                aux_middle = aux_middle->GetNext();
                middle--;
            }

            aux_cell->SetNext(aux_middle->GetNext());
            aux_middle->SetNext(aux_cell);
            this->SetSize(this->GetSize() + 1);
        }
         
        // List Methods Remove -------------------------------------
        void Remove(int pos){
            Cell* aux = Search_to_Position(pos);
            

            if (aux == NULL){
                cout << endl << "=> 404 Not Found Position !!" << endl;

            }else if(aux == this->GetInit() and !OneElement()){
                this->Remove_Init(aux);
            
            }else if(aux == this->GetLast() and !OneElement()){
                this->Remove_End(aux);
            
            }else if(aux != NULL and !OneElement()){
                this->Remove_Middle(aux);
            
            }else if(OneElement()){
                cout << endl << "=> Delete : " << aux->GetValue() << endl; 
                this->SetInit(NULL);
                this->SetLast(NULL);
                this->SetSize(this->GetSize() - 1);
                delete aux;
            }
        
        }

        void Remove_End(Cell* wash){
            this->SetLast(wash->GetNext());
            cout << endl << "=> Delete : " << wash->GetValue() << endl; 
            delete wash;
            this->SetSize(this->GetSize() - 1 );
        }

        void Remove_Init(Cell* wash){

			Cell* ant = this->GetLast();
            while(ant->GetNext()->GetNext()){
                ant = ant->GetNext();
            }

            this->SetInit(ant);
            ant->SetNext(NULL);
            cout << endl << "=> Delete : " << wash->GetValue() << endl; 
            delete wash;
            this->SetSize(this->GetSize() - 1);
            
        }

        void Remove_Middle(Cell* wash){
            Cell* ant = this->GetLast();

            while(ant->GetNext() != wash){
                ant = ant->GetNext();
            }

            ant->SetNext(wash->GetNext());
            wash->SetNext(NULL);
            cout << endl << "=> Delete : " << wash->GetValue() << endl; 
            delete wash;
            this->SetSize(this->GetSize() - 1);

        }

        // List Methods Search -------------------------------------
        
        void Search_to_value(int value){
             Cell* aux = this->GetLast();
             
             while(true){
                if(value == aux->GetValue()){
                    cout << endl << "=> " <<  aux->GetValue() << " There is in List " << endl;
                    break;
                }else if(aux->GetNext() != NULL){
                    aux = aux->GetNext();
                }else if(aux->GetNext() == NULL){
                    cout << endl << "=> 404 Not found" << endl;
                    break;
                }
            }
        }

        Cell* Search_to_Position(int pos){
            Cell* aux = this->GetLast();
            int cont = this->GetSize();

            while(true){
                if(pos == cont){
                    return aux;
                }else if(aux->GetNext() != NULL){
                    aux = aux->GetNext();
                    cont--;
                }else if(aux->GetNext() == NULL){
                    return NULL;
                }
            }
        }

        // Print List Methods --------------------------------------
        void PrintList(){      
            Cell* aux_cell = this->GetLast(); 
            if(this->Empty()){
                cout << "The List : Empty !!" << endl << endl ;
            }else{
                cout << "The List : " ;
                while(aux_cell){
                    cout << aux_cell->GetValue() << " ";
                    aux_cell = aux_cell->GetNext();
                }
                cout << endl << endl;
            }
        }
};

int menu(int choice){
    cout << "1 - Insert Init of List (Verified)" << endl;
    cout << "2 - Insert End of List (Verified)" << endl;
    cout << "3 - Insert Middle of List (Verified) " << endl;
    cout << "4 - Remove Element (Verified)" << endl;
    cout << "5 - Search Element by Value (Verified)" << endl;
    cout << "6 - Search Element by Position (Verified)" << endl;
    cout << "7 - Size of List (Verified)" << endl;
    cout << "8 - Exit" << endl;
    cin >> choice;
    return choice;
}

int main(){
    List list;
    Cell* result;
    int value,size,choice;

    system("clear");
    cout << "Program  Simple List, OO" << endl;
    cout << "ED1" << endl << endl;

    do{
        cout << endl << "- - - - - - - - - - - - - - - - - - - " << endl;
        list.PrintList();
        if(list.Empty()){
            cout << "- Enter the number of itens in the list : ";
            cin >> size;

            for(int c = 1; c <= size; c++){
                cout << "- Enter the number for cell[" << c << "] :";
                cin >> value;
                list.Insert(value);
            }
            cout << endl;
        }
        
        choice = menu(choice);

        switch(choice){
            case 1:
                cout << "Enter number for insert in Init: ";
                cin >> value;
                list.InsertCell_Init(value);
            break;
            case 2:
                cout << "Enter number for insert in the End: ";
                cin >> value;
                list.InsertCell_End(value);
            break;
            case 3:
                cout << "Enter number for insert in the list: ";
                cin >> value;
                list.InsertCell_Middle(value);
            break;
            case 4:
                cout << "Enter position for destroy element: ";
                cin >> value;
                list.Remove(value);
            break;
            case 5:
                cout << "Enter value for search element by value: ";
                cin >> value;
                list.Search_to_value(value);
            break;
            case 6:
                cout << "Enter value for search element by position: ";
                cin >> value;
                result = list.Search_to_Position(value);
                if(result == NULL){
                    cout << endl << "=> 404 Not Found Position !!" << endl;
                }else{
                    cout << endl << "=> There is position, value = " << result->GetValue() << endl;
                }
            break;
            case 7:
                cout << endl << "=> Size : " << list.GetSize() << endl;
            break;
            case 8:
                cout << endl <<" ShutDown" << endl;
            break;
            default: cout << "Enter with number right" << endl;
        }
    }while(choice != 8);

    list.PrintList();



    return 0;
}