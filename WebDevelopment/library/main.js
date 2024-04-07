const books = [];
const RENDER_EVENT = 'render-list';
const SAVED_EVENT = 'saved-list';
const STORAGE_KEY = 'BOOKSHELF_APPS';

  function generateId() {
    return +new Date();
  }

  function isStorageExist() /* boolean */ {
    if (typeof (Storage) === undefined) {
        alert('Browser kamu tidak mendukung local storage');
        return false;
    }
    return true;
  }

  function loadDataFromStorage() {
    const serializedData = localStorage.getItem(STORAGE_KEY);
    let data = JSON.parse(serializedData);
   
    if (data !== null) {
      for (const book of data) {
        books.push(book);
      }
    }

    document.dispatchEvent(new Event(RENDER_EVENT));
  }

  document.addEventListener('DOMContentLoaded', function () {
    const submitForm = document.getElementById('inputBook');
    submitForm.addEventListener('submit', function (event) {
      event.preventDefault();
      addBooks();
    });

    if (isStorageExist()) {
      loadDataFromStorage();
    }
  });

  const sudahDibaca = document.getElementById('inputBookIsComplete');
  sudahDibaca.addEventListener('change', function() {
    submitButton = document.getElementById('bookSubmit');
    if(this.checked){
        submitButton.innerHTML = 'Masukkan Buku ke rak <span>Sudah selesai dibaca</span>';
    }else{
        submitButton.innerHTML = 'Masukkan Buku ke rak <span>Belum selesai dibaca</span>';
    }
  })

  function addBooks() {
    const title = document.getElementById('inputBookTitle').value;
    const author = document.getElementById('inputBookAuthor').value;
    const year = document.getElementById('inputBookYear').value;
    let read = false;
    if(sudahDibaca.checked){
        read = true;
    }
   
    const generatedID = generateId();
    const bookObject = generateBookObject(generatedID, title, author, parseInt(year), read);
    books.push(bookObject);
    alert(`buku ${title} telah ditambahkan ke rak`);
   
    document.dispatchEvent(new Event(RENDER_EVENT));
    saveData();
  }

  document.addEventListener(RENDER_EVENT, function () {
    const uncompletedBooks = document.getElementById('incompleteBookshelfList');
    uncompletedBooks.innerHTML = '';
   
    const completedBooks = document.getElementById('completeBookshelfList');
    completedBooks.innerHTML = '';
   
    for (const bookItem of books) {
      const bookElement = makeBook(bookItem);
      if (!bookItem.isCompleted)
        uncompletedBooks.append(bookElement);
      else
        completedBooks.append(bookElement);
    }
  });
   
  function generateBookObject(id, title, author, year, isCompleted) {
    return {
      id,
      title,
      author,
      year,
      isCompleted
    }
  }

  function makeBook(bookObject) {
    const textTitle = document.createElement('h3');
    textTitle.innerText = bookObject.title;
   
    const textAuthor = document.createElement('p');
    textAuthor.innerText = "Penulis: "+bookObject.author;

    const textYear = document.createElement('p');
    textYear.innerText = "Tahun: "+bookObject.year;

    const buttonContainer = document.createElement('div');
    buttonContainer.classList.add('action');

    const swapButton = document.createElement('button');
    swapButton.classList.add('green');

    const editButton = document.createElement('button');
    editButton.innerText = "Edit buku";
    editButton.classList.add('green');
 
    const deleteButton = document.createElement('button');
    deleteButton.innerText = "Hapus buku";
    deleteButton.classList.add('red');

    if (bookObject.isCompleted) {   
        swapButton.innerText = "Belum selesai di Baca";
    } else {
        swapButton.innerText = "Selesai di Baca";
    }

    swapButton.addEventListener('click', function () {
        moveShelf(bookObject.id);
    });
    editButton.addEventListener('click', function () {
        editBook(bookObject.id);
    })
    deleteButton.addEventListener('click', function () {
       removeBook(bookObject.id);
    });

    buttonContainer.append(swapButton, editButton, deleteButton);

    const container = document.createElement('article');
    container.classList.add('book_item');
    container.append(textTitle, textAuthor, textYear, buttonContainer);
    container.setAttribute('id', `book-${bookObject.id}`);

    return container;
  }

  function moveShelf(bookId) {
    const bookTarget = findBook(bookId);
   
    if (bookTarget == null) return;

    bookTarget.isCompleted = !bookTarget.isCompleted;

    document.dispatchEvent(new Event(RENDER_EVENT));
    saveData();
  }

  function removeBook(bookId) {
    const bookTarget = findBookIndex(bookId);
   
    if (bookTarget === -1) return;

    const title = findBook(bookId).title;
    const isRead = (findBook(bookId).isCompleted) ? "Selesai dibaca" : "Belum selesai dibaca";
    alert(`buku ${title} dihapus dari rak ${isRead}`);
    
    books.splice(bookTarget, 1);
    document.dispatchEvent(new Event(RENDER_EVENT));
    saveData();
  }

  let bookIdEdit = 0;
  function editBook(bookId) {
    bookIdEdit = bookId;
    const bookTarget = findBook(bookId);
    document.getElementById('overlayer').style.visibility = 'visible';
    const editWindow = document.getElementById('edit-book');
    editWindow.style.visibility = 'visible';
    document.getElementById('editBookTitle').value = bookTarget.title;
    document.getElementById('editBookAuthor').value = bookTarget.author;
    document.getElementById('editBookYear').value = bookTarget.year;
  }

  const editForm = document.getElementById('editBookData');
  editForm.addEventListener('submit', function (event) {
    event.preventDefault();
    const bookTarget = findBook(bookIdEdit);
    bookTarget.title = document.getElementById('editBookTitle').value;
    bookTarget.author = document.getElementById('editBookAuthor').value;
    bookTarget.year = parseInt(document.getElementById('editBookYear').value);
    document.getElementById('edit-book').style.visibility = 'hidden';
    document.getElementById('overlayer').style.visibility = 'hidden';
    document.dispatchEvent(new Event(RENDER_EVENT));
    saveData();
    bookIdEdit = 0;
  })



  function findBook(bookId) {
    for (const Item of books) {
      if (Item.id === bookId) {
        return Item;
      }
    }
    return null;
  }

  function findBookIndex(bookId) {
    for (let i = 0; i<books.length; i++) {
        if (books[i].id === bookId) {
          return i;
        }
      }
      return -1;
  }

  function saveData() {
    if (isStorageExist()) {
      const parsed = JSON.stringify(books);
      localStorage.setItem(STORAGE_KEY, parsed);
      document.dispatchEvent(new Event(SAVED_EVENT));
    }
  }

  document.addEventListener(SAVED_EVENT, function () {
    console.log(localStorage.getItem(STORAGE_KEY));
  });

  const searchForm = document.getElementById('searchBook');
  searchForm.addEventListener('submit', function(event) {
    event.preventDefault();
    const keyword = document.getElementById('searchBookTitle').value;
    const uncompletedBooks = document.getElementById('incompleteBookshelfList');
    uncompletedBooks.innerHTML = '';
   
    const completedBooks = document.getElementById('completeBookshelfList');
    completedBooks.innerHTML = '';
   
    let count = 0;
    for (const bookItem of books) {
      const title = bookItem.title;
      if(title.includes(keyword)){
        count++;
        const bookElement = makeBook(bookItem);
        if (!bookItem.isCompleted)
          uncompletedBooks.append(bookElement);
        else
          completedBooks.append(bookElement);
      }
    }
    alert(`ditemukan hasil pencarian sebanyak ${count}`);
  })
