#include<iostream> 
#include <list>
#include <vector>
#include <iterator> 

using namespace std; 

class Hash {
	int TH;
    list<int> *table; 
public: 
	Hash(int size) {
		this->TH = size; 
		this->table = new list<int>[this->TH];
	} 
	
	int hashFunction(int value) {
		return (value % this->TH);
	}

	void insertItem(int value) { 
		int index = this->hashFunction(value); 
		this->table[index].push_back(value);  
	} 
	
	void deleteItem(int key) { 
		// get the hash index of key 
		int index = hashFunction(key); 
		
		// find the key in (index)th list 
		list <int> :: iterator i; 
		for (i = table[index].begin(); 
				i != table[index].end(); i++ ){ 
			if (*i == key) 
			break; 
		} 
		
		// if key is found in hash table, remove it 
		if (i != table[index].end())
			table[index].erase(i);
	}

	void displayHash() {
		cout << endl <<"=> Result Hash: "<< endl;
		for (int i = 0; i < this->TH; i++) {
			cout << "[" << i << "]";
			for (auto x : this->table[i])
				cout << " --> " << x;
				cout << endl;
		}
	}

	void loadFactor() {
		int major = 0;
		for(int i = 0; i < this->TH; i++) {
			if(this->table[i].size() > major)
				major = this->table[i].size();
			else
				continue;
		}
		cout << "=> Load Factor (λ): " << major << endl;
	}
}; 
  
void printCurrentValues(vector<int> values){
	cout << "=> Values Stories [ ";
	for (auto it = values.begin(); it != values.end(); it++) 
    	cout << *it << ", "; 
	cout << " ]" << endl;
}

int main() {
  int size, current_value;
  vector<int> values;
  cout << "Simple Hashing (With Lists)" << endl;
  cout << "---> Insert TH of Hash : ";
  cin >> size;

  Hash hash(size);

  for (;;) {
		cout << endl <<"---> (mod: " << size <<") Insert values : ";
		cin >> current_value;
		values.push_back(current_value);
		hash.insertItem(current_value);
		hash.displayHash();
		printCurrentValues(values);
		hash.loadFactor();
  }
       
//   hash.deleteItem(12); 
  
  return 0; 
} 