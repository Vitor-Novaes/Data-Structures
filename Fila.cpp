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
        Cell* Getnext(){
            return this->next;
        }
        
};

class Row{
    private:
        Cell* init;
        Cell* last; 
    public:

        // Row Methods -------------------------------------------
        Row(){
            this->SetInit(NULL);
            this->SetLast(NULL);
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

        // Row Methods validates ----------------------------------

        bool Empty(){
            return (this->GetInit() == NULL);
        }        

        void CountElementsRow(){
            int count = 0;
            Cell *aux = this->GetInit();
            
            while(aux){
                count++;
                aux = aux->Getnext();
            }
            cout << "=> Size : " << count ;
        
        }

        // Insert Row Methods -------------------------------------
        void InsertRow(int value){
            if (this->Empty()){
                this->InsertCell_RowEmpty(value);
            }else{
                this->InsertCell_RowNotEmpty(value);
            }
        }

        void InsertCell_RowEmpty(int value){
            init = new Cell(value);
            this->SetLast(init);
        }

        void InsertCell_RowNotEmpty(int value){
            Cell* aux_cell = new Cell(value);
            this->GetLast()->SetNext(aux_cell);
            this->SetLast(aux_cell);
        }

        //Remove Row Methods --------------------------------------
        
        void RemoveElementRow() {
            if(this->Empty()){
                cout << "=> Row Empty !!" << endl << endl;
            }else{
                Cell *head = this->GetInit();
                
                this->SetInit(head->Getnext());
                head->SetNext(NULL);
                
                delete head;
            }

        }
        
        void DestroyRow(){
            while(this->GetInit() != NULL){
                this->RemoveElementRow();
            }
        }

        // Search Row Methods -------------------------------------
        bool Search(int value){
            Row aux_row;
            Cell* aux_cell = this->GetInit();
            bool found = false;


            if(this->Empty()){
                cout << endl << "=> Row Empty !!";
            }else{
                while(aux_cell != NULL ){
                    if(aux_cell->GetValue() == value){
                       found = true;
                    }
                    aux_row.InsertRow(aux_cell->GetValue());
                    this->RemoveElementRow();
                    aux_cell = this->GetInit();
                }

                while(aux_row.GetInit() != NULL){
                    this->InsertRow(aux_row.GetInit()->GetValue());
                    aux_row.RemoveElementRow();
                }

            }

            return found;

        }

        // Print Row Methods --------------------------------------
        void PrintRow(bool *search, int choice){      
            Cell* aux_cell = this->GetInit(); 
            
            this->CountElementsRow();
            if(this->Empty()){
                cout << endl << "=> Row Empty !!";
            }else{
                cout << endl << "=> The Row : ";
            }

            if(choice == 4 and *search == false){
                cout << endl <<"=> There is not the element !" << endl;
            }else if(choice == 4 and *search == true){
                *search = false;
                cout << endl << "=> There is the element !" << endl;
            }

            while(aux_cell){
                cout << aux_cell->GetValue() << " ";
                aux_cell = aux_cell->Getnext();
            }
            cout << endl << endl;
        }
};


int main(){
    Row row;
    int value,choice;
    bool search = false;

    system("clear");
    cout << "Program Row, OO, FIFO (First in, First out)" << endl;
    cout << "ED1" << endl << endl;
    cout << endl << "=> Enter any to continue . . . " << endl;
    getchar();


    while(true){
        system("clear");
        cout << endl << "Program : Struct Row ";
        cout << endl << "----------------------------------- " << endl;  
        row.PrintRow(&search,choice);
        cout << "1 - Insert element on Row (Verified)" << endl;
        cout << "2 - Remove element on Row (Verified)" << endl;
        cout << "3 - Destroy elements the Row (Verified)" << endl;
        cout << "4 - Search by value (WIP)" << endl;
        cout << "0 - Exit" << endl;
        cin >> choice;
        
        switch(choice){
            case 1 :
                cout << "=> Enter the number for cell insert last position: ";
                cin >> value;
                row.InsertRow(value);
            break;
            case 2 :
                row.RemoveElementRow();
            break;
            case 3 :
                char sure;
                cout << "=> Are you sure ? (y/n): ";
                cin >> sure;
                if(sure == 'y'){
                    row.DestroyRow();
                }else{
                    continue;
                }
            break;
            case 4:
                cout << "=> Enter Value for search: ";
                cin >> value;
                search = row.Search(value);
            break;
            case 0 : exit(0);break;
            default : ;
        }        
    }

    return 0;
}