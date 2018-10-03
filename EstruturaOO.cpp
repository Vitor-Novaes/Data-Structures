#include <iostream>
#include <string>
using namespace std;

class Cell{
    private:
        int value;
        Cell* next;
        Cell* previous;
    public:
        Cell(int value){
            this->next = NULL;
            this->previous = NULL;
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
        Cell* GetPrevious(){
            return this->previous;
        }
        Cell* GetNext(){
            return this->next;
        }
};

class List{
    private:
        Cell* init;
        Cell* last; 
    public:

        // List Methods -------------------------------------------
        List(){
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

        // List Methods validates ----------------------------------
        

        // Insert List Methods -------------------------------------
        void Insert(int value){
            if (this->GetInit() == NULL){
                this->InsertCell_ListEmpty(value);
            }else{
                this->InsertCell_ListNotEmpty(value);
            }
        }
        void InsertCell_ListEmpty(int value){
            init = new Cell(value);
            this->SetLast(init);
        }
        void InsertCell_ListNotEmpty(int value){
            Cell* aux_cell = new Cell(value);
            aux_cell->SetNext(last);
            this->GetLast()->SetPrevious(aux_cell);
            this->SetLast(aux_cell);
        }

        // Print List Methods --------------------------------------
        void PrintList(){      
            Cell* aux_cell = this->GetInit(); 
    
            while(aux_cell){
                cout << aux_cell->GetValue() << " ";
                aux_cell = aux_cell->GetPrevious();
            }
            cout << endl;
        }
};


int main(){
    List list;
    int value,size;
    system("clear");
    cout << "Program List Dulp, OO, Listing odd in first and even in last" << endl;
    cout << "ED1" << endl << endl;
    cout << "- Enter the number of items in the list : ";
    cin >> size;

    for(int c = 0; c < size; c++){
        cout << "- Enter the number for cell[" << c << "] :";
        cin >> value;
        list.Insert(value);
    }

    list.PrintList();


    return 0;
}