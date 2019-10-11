#include <iostream>
#include <string>
#include <vector>
using namespace std;

class Cell{
    int value;
    int height;
    Cell *left;
    Cell *right;
    Cell *parent;
public:
    Cell(int value){
        this->SetLeft(NULL);
        this->SetRight(NULL);
        this->SetParent(NULL);
        this->SetValue(value);
        this->SetHeight(NULL);
    }
    void SetValue(int value){
        this->value = value;
    }
    void SetHeight(int height){
        this->height = height;
    }

    void SetParent(Cell* parent){
        this->parent = parent;
    }
    
    void SetRight(Cell* right){
        this->right = right;
    }
    void SetLeft(Cell* left){
        this->left = left;
    }

    int GetHeight(){
        return this->height;
    }
    int GetValue(){
        return this->value;
    }
    Cell* GetLeft(){
        return this->left;
    }
    Cell* GetRight(){
        return this->right;
    }
    Cell* GetParent(){
        return this->parent;
    }
};

class AVL{
    Cell* root;
public:
    AVL(){
        this->SetRoot(NULL);
    }
    Cell* GetRoot(){
        return this->root;
    }   
    void SetRoot(Cell* root){
        this->root = root;
    }

    // Methods Inserts BST -----------------------------
    void InsertTree(int value){
        if(this->Empty()){
            this->InsertTreeEmpty(value);
        }else{
            // this->InsertTreeNotEmpty(value);
            this->InsertTreeNotEmptyRecursive(this->GetRoot(),value);
        }
    }

    void InsertTreeEmpty(int value){
        Cell* new_root = new Cell(value);
        this->SetRoot(new_root);
        cout << "=> Root : " << this->GetRoot()->GetValue() << endl;
    }
    
    void InsertTreeNotEmptyRecursive(Cell* tmp, int value){
        // Caminhada para esquerda
        if(tmp->GetValue() >= value){
            if(tmp->GetLeft() == NULL){
                cout << "Inserindo "<< value << " a Esquerda de " << tmp->GetValue() << endl;
                Cell* new_cell = new Cell(value);
                new_cell->SetParent(tmp);
                tmp->SetLeft(new_cell);
            }else{
                this->InsertTreeNotEmptyRecursive(tmp->GetLeft(),value);
            }
        }

        // Caminhada para direita
        if(tmp->GetValue() < value){
            if(tmp->GetRight() == NULL){
                cout << "Inserindo "<< value << " a Direita de " << tmp->GetValue() << endl;
                Cell* new_cell = new Cell(value);
                new_cell->SetParent(tmp);
                tmp->SetRight(new_cell);
            }else{
                this->InsertTreeNotEmptyRecursive(tmp->GetRight(),value);
            }
        }


    }
    
    // Methods Remove ------------------------------------
    void RemoveTree(int value){
        // Retorna a Célula que será removida
        Cell* tofree = this->TreeSearch(this->GetRoot(),value);
        cout << tofree->GetValue() << endl;

        // Verifica qual caso de Remoção
        if(this->Empty() == true or tofree == NULL){
            cout << "Invalid operation (Cell empty or Cell not found)" << endl;        
        
        // Caso 1 : Remoção da Folha
        }else if(tofree->GetLeft() == NULL and tofree->GetRight() == NULL){
            this->RemoveLeafTree(tofree);

        // Caso 2 : Remoção da Célula com 1 descendente
        }else if(tofree->GetLeft() == NULL or tofree->GetRight() == NULL){
            this->RemoveParentOneTree(tofree);

        // Caso 3 : Remoção da Célula com 2 descendentes
        }else if(tofree->GetLeft() != NULL and tofree->GetRight() != NULL){
            this->RemoveParentTwoTree(tofree);
        }
    }

    void RemoveLeafTree(Cell* tofree){
        Cell* parent = tofree->GetParent();
        cout << "-> Removendo : " << tofree->GetValue() << ": Filho de :" << parent->GetValue() << endl;
        delete tofree;
        
        // Atualiza ponteiros do parent
        if(parent->GetRight() == tofree){
            parent->SetRight(NULL);
        }else{
            parent->SetLeft(NULL);
        }
        
    }

    void RemoveParentOneTree(Cell* tofree){
        Cell* parent = tofree->GetParent();

        // Pai Herdando do lado direito
        if(parent->GetRight() == tofree){
            
            // Verifica qual lado da célula removida será o herdeiro e atualiza ponteiros
            if(tofree->GetLeft() == NULL){
                parent->SetRight(tofree->GetRight());
                tofree->GetRight()->SetParent(parent);
                cout << "-> Herança de remoção : " << parent->GetValue() << ": Herda Filho :" << tofree->GetRight()->GetValue() << endl;
            }else{
                parent->SetRight(tofree->GetLeft());
                tofree->GetLeft()->SetParent(parent);
                cout << "-> Herança de remoção : " << parent->GetValue() << ": Herda Filho :" << tofree->GetLeft()->GetValue() << endl;
            }
        }
        
        // Pai Herdando do lado esquerdo           
        if(parent->GetLeft() == tofree){

            // Verifica qual lado da célula removida será o herdeiro e atualiza ponteiros
            if(tofree->GetLeft() == NULL){
                parent->SetLeft(tofree->GetRight());
                tofree->GetRight()->SetParent(parent);
                cout << "-> Herança de remoção : " << parent->GetValue() << ": Herda Filho :" << tofree->GetRight()->GetValue() << endl;
            }else{
                parent->SetLeft(tofree->GetLeft());
                tofree->GetLeft()->SetParent(parent);
                cout << "-> Herança de remoção : " << parent->GetValue() << ": Herda Filho :" << tofree->GetLeft()->GetValue() << endl;
            }
        }
        
        // Removendo
        cout << "-> Removendo : " << tofree->GetValue() << ": Filho de :" << parent->GetValue() << endl;
        delete tofree;

    }

    void RemoveParentTwoTree(Cell* tofree){
        Cell* replace = FindMinTree(tofree->GetRight()); // Pega o menor elemento dos maiores (1 direita, esquerda)
        tofree->SetValue(replace->GetValue());

        if(replace->GetLeft() == NULL and replace->GetRight() == NULL){
            this->RemoveLeafTree(replace);
        }else{
            this->RemoveParentOneTree(replace);
        }
    }

    void FreeTree(){
        while(this->GetRoot() != NULL){
            this->RemoveTree(this->GetRoot()->GetValue());
        }
    }


    // Methods Search ------------------------------------
    Cell* TreeSearch(int value){
      return this->TreeSearch(this->GetRoot(),value);
    }

    Cell* TreeSearch(Cell* tmp, int value){
        
        // Condição de parada: NOT FOUND
        if(tmp == NULL){
            return NULL;
        // Condição de parada: FOUND
        }else if(tmp->GetValue() == value){
            return tmp;
        // Chamada recursiva BST
        }else if(tmp->GetValue() > value){
            this->TreeSearch(tmp->GetLeft(),value);
        }else{
            this->TreeSearch(tmp->GetRight(),value);
        }
    }

    // Encontrar o menor elemento de uma sub-árvore
    Cell* FindMinTree(Cell* tmp){
        if(tmp->GetLeft() == NULL){
            return tmp;
        }else{
            FindMinTree(tmp->GetLeft());
        }
    }

    // Encontrar o maior elemento de uma sub-árvore
    Cell* FindMaxTree(Cell* tmp){
        if(tmp->GetRight() == NULL){
            return tmp;
        }else{
            FindMaxTree(tmp->GetRight());
        }
    }

    // Methods Rotate ------------------------------------
    void BalanceUpdate(Cell* tmp){
        
        if(tmp != NULL){
            int balance_center = this->Balance(tmp);
            int balance_left = this->Balance(tmp->GetLeft());
            int balance_right = this->Balance(tmp->GetRight());
            
            if (balance_center >= 2 && balance_right >= 1 ) {
                // this->RotationSimpleLeft();
                cout << endl << "=> Rotation :" << tmp->GetValue() << " Simple Left";
            } else if (balance_center <= -2 && balance_left <= -1 ) {
                cout << endl << "=> Rotation :" << tmp->GetValue() << " Simple Right";
                this->RotationSimpleRight(tmp);
            } else if (balance_center >= 2 && balance_right <= -1 ) {
                // this->RotationDoubleRightLeft();
                cout << endl << "=> Rotation :" << tmp->GetValue() << " Double Right Left";
            } else if (balance_center <= -2 && balance_left >= 1 ) {
                // this->RotationDoubleLeftRight();
                cout << endl << "=> Rotation :" << tmp->GetValue() << " Double Left Right";
            }
        } else {
            this->BalanceUpdate(tmp->GetLeft());
            this->BalanceUpdate(tmp->GetRight());
        }
    }

    void RotationSimpleRight(Cell* tmp) {
        if (tmp->GetParent()->GetRight() == tmp){
            tmp->GetParent()->SetRight(tmp->GetLeft());    
        } else {
            tmp->GetParent()->SetLeft(tmp->GetLeft());
        }

        tmp->GetLeft()->SetParent(tmp->GetParent());
        tmp->SetParent(tmp->GetLeft());

        tmp->SetLeft(tmp->GetLeft()->GetRight());
        tmp->GetParent()->GetRight()->SetParent(tmp);
        tmp->GetParent()->SetRight(tmp);
    }

    void RotationSimpleLeft() {

    }

    void RotationDoubleRightLeft() {

    }

    void RotationDoubleLeftRight() {

    }

    // Methods Validate ------------------------------------
    bool Empty(){
        return this->GetRoot() == NULL;
    }

    int height(Cell* tmp){
        if(tmp == NULL)
            return 0;
        else
            return 1 + max(height(tmp->GetRight()),height(tmp->GetLeft()));
    }

    int max(int height1, int height2){
        if(height1 > height2)
            return height1;
        else
            return height2;
    }
    
    int Balance(Cell *tmp){
        if(tmp == NULL)
            return 0;
        
        int balance = height(tmp->GetRight()) - height(tmp->GetLeft());
        return balance;
    }
    
    // Methods Print ------------------------------------
    void PrintTree(Cell *aux){
        if(aux->GetRight() != NULL){
            PrintTree(aux->GetRight());
        }

        cout << "Célula : " << aux->GetValue() << " : Altura : " << this->height(aux) << " : Balance : " << this->Balance(aux)<< endl;

        if(aux->GetLeft() != NULL){
            PrintTree(aux->GetLeft());
        }
    }

    void PrintInOrder(Cell* tmp){
        if(tmp == NULL){
            return;
        }
        this->PrintInOrder(tmp->GetRight());
        cout << tmp->GetValue() << endl;
        this->PrintInOrder(tmp->GetLeft());
    }

    void PrintPreOrder(Cell* tmp){
        if(tmp == NULL){
            return;
        }
        cout << tmp->GetValue() << endl;
        this->PrintPreOrder(tmp->GetRight());
        this->PrintPreOrder(tmp->GetLeft());
    }

    void PrintPosOrder(Cell* tmp){
        if(tmp == NULL){
            return;
        }
        this->PrintPosOrder(tmp->GetRight());
        this->PrintPosOrder(tmp->GetLeft());
        cout << tmp->GetValue() << endl;
    }

    int PrintExpression(Cell* tmp){
        int right = 1, left = 1;
        if( tmp != NULL){
            cout << "[";
            cout << tmp->GetValue();
            left = this->PrintExpression(tmp->GetLeft());
            right = this->PrintExpression(tmp->GetRight());
            if(left == 0 && right == 0) cout << "(~)(~)";
            if(right == 0 && left != 0) cout << "[()(~)]";
            if(right != 0 && left == 0) cout << "[(~)()]";

            cout << "]";
        } else return 0;        
    }


};

void current_values_insert(vector<int> values){
    cout << "=> Values Stories [ ";
	for (auto it = values.begin(); it != values.end(); it++) 
    	cout << *it << ", "; 
	cout << " ]" << endl;
}

int main(){
    system("clear");

    AVL Tree;
    int current_value, size, choice;
    vector<int> values;

    cout << "AVL complete methods - 0.1.0" << endl;
    cout <<"--> Insert initial size elements AVL : ";
    cin >> size;
    
    for (int i = 0; i < size; i++) {
		cout << endl <<"--> (inital elements: " << size <<") Insert values : ";
		cin >> current_value;
		values.push_back(current_value);
        Tree.InsertTree(current_value);
        current_values_insert(values);
        if (i >= 2)
            Tree.BalanceUpdate(Tree.GetRoot());
    }

    while(true){
        cout <<"\n\n1 - Insert element AVL" << endl;
        cout << "2 - Print AVL" << endl;
        cout << "3 - Remove element AVL" << endl;
        cout << "4 - Count elements AVL" << endl;        
        cout << "5 - Destroy elements the AVL" << endl;
        cout << "6 - Height AVL" << endl;
        cout << "7 - Search element AVL" << endl;
        cout << "0 - Exit" << endl << endl;
        int choice;
        cin >> choice;

        switch(choice){
            case 1 :
                cout << "- Enter the number: ";
                cin >> current_value;
                values.push_back(current_value);
                Tree.InsertTree(current_value);
                current_values_insert(values);
                Tree.BalanceUpdate(Tree.GetRoot());
            break;
            case 2 :
                cout << "=> Height : (" << Tree.height(Tree.GetRoot()) << ")";
                cout << "=> Pos Order => ";
                Tree.PrintPosOrder(Tree.GetRoot());
                cout << "=> Pre Order => ";
                Tree.PrintPreOrder(Tree.GetRoot());
                cout << "=> Expression AVL => ";
                Tree.PrintExpression(Tree.GetRoot());
                cout << endl << "Legends: " << endl << "[value(~)(~)] ---- Leaf " << endl;
                cout << "[()(~)] ---------- Unique child in left " << endl;
                cout << "[(~)()] ---------- Unique child in right" << endl;
                cout << "[[Value Left][Value Right]] -- Order expression" << endl << endl;
            break;
            case 3 :
                // Tree.RemoveElementRow();
            break;
            case 4 :
                // Tree.CountElementsRow();
            break;
            case 5 :
                // Tree.DestroyRow();
            break;
            case 0 : exit(0);
        }        
    }

    // Tree.InsertTree(8); // root
    // Tree.InsertTree(3);
    // Tree.InsertTree(1);
    // Tree.InsertTree(10);
    // Tree.InsertTree(6);
    // Tree.InsertTree(4);
    // Tree.InsertTree(7);
    // Tree.InsertTree(14);
    // Tree.InsertTree(13);

    return 0;
}
