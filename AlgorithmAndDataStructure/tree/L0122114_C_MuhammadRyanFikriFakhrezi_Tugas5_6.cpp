#include <iostream>
#include <queue>
#include <algorithm>

struct Node {
	int data;
	Node* left;
	Node* right;
};

Node* CreateNode(int data)
{
	Node* newNode = new Node();
	if (!newNode) {
		std::cout << "Memory error\n";
		return NULL;
	}
	newNode->data = data;
	newNode->left = newNode->right = NULL;
	return newNode;
}

Node* InsertNode(Node* root, int data)
{
	if (root == NULL) {
		root = CreateNode(data);
		return root;
	}

	std::queue<Node*> q;
	q.push(root);

	while (!q.empty()) {
		Node* temp = q.front();
		q.pop();

		if (temp->left != NULL)
			q.push(temp->left);
		else if(data<temp->data){
			temp->left = CreateNode(data);
			return root;
		}

		if (temp->right != NULL)
			q.push(temp->right);
		else if(data>temp->data){
			temp->right = CreateNode(data);
			return root;
		}
	}
}

struct Node* minValueNode(struct Node* node)
{
	struct Node* current = node;

	/* loop down to find the leftmost leaf */
	while (current && current->left != NULL)
		current = current->left;

	return current;
}

struct Node* deleteNode(struct Node* root, int data)
{
	if (root == NULL)
		return root;

	if (data < root->data)
		root->left = deleteNode(root->left, data);

	else if (data > root->data)
		root->right = deleteNode(root->right, data);

	else {
		if (root->left == NULL && root->right == NULL)
			return NULL;
		else if (root->left == NULL) {
			struct Node* temp = root->right;
			free(root);
			return temp;
		}
		else if (root->right == NULL) {
			struct Node* temp = root->left;
			free(root);
			return temp;
		}

		struct Node* temp = minValueNode(root->right);
		root->data = temp->data;
		root->right = deleteNode(root->right, temp->data);
	}
	return root;
}

struct Node* search(struct Node* root, int data)
{
	// Base Cases: root is null or data is present at root
	if (root == NULL || root->data == data)
	return root;
	
	// Key is greater than root's data
	if (root->data < data)
	return search(root->right, data);

	// Key is smaller than root's data
	return search(root->left, data);
}

int height(Node* node)
{
    if (node == NULL)
        return 0;

    return 1 + std::max(height(node->left), height(node->right));
}

bool isBalanced(Node* root)
{
    int left_height;
    int right_height;
    if (root == NULL)
        return 1;
    left_height = height(root->left);
    right_height = height(root->right);
 
    if (abs(left_height - right_height) <= 1 && isBalanced(root->left)
        && isBalanced(root->right))
        return 1;
    return 0;
}

void inorder(Node* temp)
{
	if (temp == NULL)
		return;

	inorder(temp->left);
	std::cout << temp->data << ' ';
	inorder(temp->right);
}

int main()
{
	Node* root = new Node();
    int num[7] = {50, 30, 20, 40, 70, 60, 80};
    for(int i=0; i<sizeof(num)/sizeof(num[0]); i++)
    {
        InsertNode(root, num[i]);
    }
	std::cout << "Inorder traversal before insertion: ";
	inorder(root);
	std::cout << std::endl;

	InsertNode(root, 12);

	std::cout << "Inorder traversal after insertion: ";
	inorder(root);
	std::cout << std::endl;

	deleteNode(root, 30);

	std::cout << "Inorder traversal after insertion: ";
	inorder(root);
	std::cout << std::endl;

	if(isBalanced(root))
		std::cout << "The tree is balanced";
	else
		std::cout << "The tree is not balanced";

	return 0;
}
