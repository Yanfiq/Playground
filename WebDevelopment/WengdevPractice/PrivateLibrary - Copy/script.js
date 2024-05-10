$(document).ready(function () {
  $.ajax({
    url: "read.php",
    type: "GET",
    data: {
      table_name: "book_unfinished",
      keyword: ""
    },
    dataType: "json",
    success: function(data) {
        const bookList = $("#book_unfinished");
        if (data.length > 0) {
            $.each(data, function(index, book) {
                const { isbn, title, author, year } = book;
                const article = $("<article>").addClass("book_item").html(`
                    <h3 class="book_title">${title}</h3>
                    <p class="book_isbn">ISBN: ${isbn}</p>
                    <p class="book_author">Author: ${author}</p>
                    <p class="book_year">Year: ${year}</p>
                    <div class="action">
                        <button class="green book_edit_btn">Edit book</button>
                        <button class="green book_swap_shelf_btn">Finished reading</button>
                        <button class="red book_delete_btn">Delete book</button>
                    </div>
                `);
                bookList.append(article);
            });
        } else {
            bookList.html("No data found.");
        }
    },
    error: function(xhr, status, error) {
        console.error("Error fetching data:", error);
    }
  });

  $.ajax({
    url: "read.php",
    type: "GET",
    data: {
      table_name: "book_finished",
      keyword: ""
    },
    dataType: "json",
    success: function(data) {
        const bookList = $("#book_finished");
        if (data.length > 0) {
            $.each(data, function(index, book) {
                const { isbn, title, author, year } = book;
                const article = $("<article>").addClass("book_item").html(`
                    <h3 class="book_title">${title}</h3>
                    <p class="book_isbn">ISBN: ${isbn}</p>
                    <p class="book_author">Author: ${author}</p>
                    <p class="book_year">Year: ${year}</p>
                    <div class="action">
                        <button class="green book_edit_btn">Edit book</button>
                        <button class="green book_swap_shelf_btn">Re-read book</button>
                        <button class="red book_delete_btn">Delete book</button>
                    </div>
                `);
                bookList.append(article);
            });
        } else {
            bookList.html("No data found.");
        }
    },
    error: function(xhr, status, error) {
        console.error("Error fetching data:", error);
    }
  });
});

$('#inputBook').submit(function (event) {
  event.preventDefault();
  $.ajax({
    url: 'create.php',
    method: 'POST',
    data: {
      isbn: $('#inputBook input[name="isbn"]').val(),
      title: $('#inputBook input[name="title"]').val(),
      author: $('#inputBook input[name="author"]').val(),
      year: $('#inputBook input[name="year"]').val(),
      table_name: $("#is_finished").is(':checked') ? "book_finished" : "book_unfinished"
    },
    success: function (response) {
      console.log(response);
    },
    error: function (xhr, status, error) {
      console.error('Error:', error);
    }
  });
});

$(document).on('click', '.book_delete_btn', function () {
  var bookItem = $(this).closest('.book_item');
  var parentElement = bookItem.parent().attr('id') == 'book_finished' ? 'book_finished' : 'book_unfinished';

  var isbn = bookItem.find('.book_isbn').text().split(':')[1].trim();

  $.ajax({
    url: 'delete.php',
    method: 'POST',
    data: {
      isbn: isbn,
      table_name: parentElement
    },
    success: function (response) {
      bookItem.remove();
      console.log(response);
    },
    error: function (xhr, status, error) {
      console.log('Element deletion failed')
    }
  });
});

$(document).on('click', '.book_swap_shelf_btn', function () {
  var bookItem = $(this).closest('.book_item');
  var parentElement = bookItem.parent().attr('id') == 'book_finished' ? 'book_finished' : 'book_unfinished';
  var destinationShelf = parentElement == "book_finished" ? "book_unfinished" : "book_finished";
  var title = bookItem.find('.book_title').text();
  var isbn = bookItem.find('.book_isbn').text().split(':')[1].trim();
  var author = bookItem.find('.book_author').text().split(':')[1].trim();
  var year = bookItem.find('.book_year').text().split(':')[1].trim();

  $.ajax({
    url: 'create.php',
    method: 'POST',
    data: {
      isbn: isbn,
      title: title,
      author: author,
      year: year,
      table_name: destinationShelf
    },
    success: function (response) {
      console.log(response);
    },
    error: function (xhr, status, error) {
      console.error(xhr, status, error);
    }
  });

  $.ajax({
    url: 'delete.php',
    method: 'POST',
    data: {
      isbn: isbn,
      table_name: parentElement
    },
    success: function (response) {
      console.log(response);
      $("#"+destinationShelf).append(bookItem);
      // bookItem.remove();
    },
    error: function (xhr, status, error) {
      console.error(xhr, status, error);
    }
  });
});

$(document).on('click', '.book_edit_btn', function () {
  console.log(this);
  $('#overlayer').css("visibility", "visible");
  $('#edit_book').css("visibility", "visible");
  var bookItem = $(this).closest('.book_item');
  var parentElement = bookItem.parent().attr('id') == 'book_finished' ? 'book_finished' : 'book_unfinished';
  var title = bookItem.find('.book_title').text();
  var isbn = bookItem.find('.book_isbn').text().split(':')[1].trim();
  var author = bookItem.find('.book_author').text().split(':')[1].trim();
  var year = bookItem.find('.book_year').text().split(':')[1].trim();

  // $('#edit_book').find('#editBookIsbn').val(isbn);
  $('#edit_book').find('#edit_book_year').val(year);
  $('#edit_book').find('#edit_book_title').val(title);
  $('#edit_book').find('#edit_book_author').val(author);
  $('#edit_book').find('#current_isbn').val(isbn);
  $('#edit_book').find('#current_table').val(parentElement);
  console.log($('#edit_book').find('#current_table').val(), $('#edit_book').find('#current_isbn').val())
})

$(document).on('click', '#save_edit', function () {
  var form = $('#edit_book_data');
  var table = $('#edit_book').find('#current_table').val()
  var isbn = $('#edit_book').find('#current_isbn').val();
  var title = form.find('#edit_book_title').val();
  var author = form.find('#edit_book_author').val();
  var year = form.find('#edit_book_year').val();
  console.log(isbn + table);
  $.ajax({
    url: 'update.php',
    method: 'POST',
    data: {
      table: table,
      isbn: isbn,
      title: title,
      author: author,
      year: year
    },
    success: function (response) {
      console.log(response);
    },
    error: function (xhr, status, error) {
      console.error('Error:', error);
    }
  });
  $('#overlayer').css("visibility", "hidden");
  $('#edit_book').css("visibility", "hidden");
});

$('#is_finished').on('click', function () {
  var id = parseInt($(this).val(), 10);
  if ($(this).is(":checked")) {
    $('#finished_label').text("Finished");
  } else {
    $('#finished_label').text("Unfinished");
  }
});

$("#search_keyword").on('input', function(event){
  event.preventDefault();
  $("#book_unfinished").empty();
  $("#book_finished").empty();
  keyword = $("#search_keyword").val();
  $.ajax({
    url: "read.php",
    type: "GET",
    data: {
      table_name: "book_unfinished",
      keyword: keyword
    },
    dataType: "json",
    success: function(data) {
        const bookList = $("#book_unfinished");
        if (data.length > 0) {
            $.each(data, function(index, book) {
                const { isbn, title, author, year } = book;
                const article = $("<article>").addClass("book_item").html(`
                    <h3 class="book_title">${title}</h3>
                    <p class="book_isbn">ISBN: ${isbn}</p>
                    <p class="book_author">Author: ${author}</p>
                    <p class="book_year">Year: ${year}</p>
                    <div class="action">
                        <button class="green book_edit_btn">Edit book</button>
                        <button class="green book_swap_shelf_btn">Finished reading</button>
                        <button class="red book_delete_btn">Delete book</button>
                    </div>
                `);
                bookList.append(article);
            });
        } else {
            bookList.html("No data found.");
        }
    },
    error: function(xhr, status, error) {
        console.error("Error fetching data:", error);
    }
  });

  $.ajax({
    url: "read.php",
    type: "GET",
    data: {
      table_name: "book_finished",
      keyword: keyword
    },
    dataType: "json",
    success: function(data) {
        const bookList = $("#book_finished");
        if (data.length > 0) {
            $.each(data, function(index, book) {
                const { isbn, title, author, year } = book;
                const article = $("<article>").addClass("book_item").html(`
                    <h3 class="book_title">${title}</h3>
                    <p class="book_isbn">ISBN: ${isbn}</p>
                    <p class="book_author">Author: ${author}</p>
                    <p class="book_year">Year: ${year}</p>
                    <div class="action">
                        <button class="green book_edit_btn">Edit book</button>
                        <button class="green book_swap_shelf_btn">Re-read book</button>
                        <button class="red book_delete_btn">Delete book</button>
                    </div>
                `);
                bookList.append(article);
            });
        } else {
            bookList.html("No data found.");
        }
    },
    error: function(xhr, status, error) {
        console.error("Error fetching data:", error);
    }
  });
});