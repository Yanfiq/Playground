style_booklist_article="book_item flex flex-col border border-solid border-black rounded-lg p-4 mt-4";
style_booklist_h3_title="book_title font-bold my-1.5";
style_booklist_p_isbn="book_isbn my-1.5";
style_booklist_p_author="book_author my-1.5";
style_booklist_p_year="book_year my-1.5";
style_booklist_div_action="action flex flex-row gap-2";
style_booklist_btn_edit="border-2 border-solid border-green-400 p-2 mb-1 rounded cursor-pointer bg-green-400 text-white hover:bg-transparent hover:text-green-400 duration-200 hover:duration-200 book_edit_shelf_btn";
style_booklist_btn_swap="border-2 border-solid border-green-400 p-2 mb-1 rounded cursor-pointer bg-green-400 text-white hover:bg-transparent hover:text-green-400 duration-200 hover:duration-200 book_swap_shelf_btn";
style_booklist_btn_del="border-2 border-solid border-red-400 p-2 mb-1 rounded cursor-pointer bg-red-400 text-white hover:bg-transparent hover:text-red-400 duration-200 hover:duration-200 book_delete_btn"

$(document).ready(function () {
  $.ajax({
    url: "../public/php/read.php",
    type: "GET",
    data: {
      table_name: "book_unfinished",
      keyword: ""
    },
    dataType: "json",
    success: function (data) {
      const bookList = $("#book_unfinished");
      if (data.length > 0) {
        $.each(data, function (index, book) {
          const { isbn, title, author, year } = book;
          const article = $("<article>").addClass(style_booklist_article);
          const h3_title = $("<h3>").addClass(style_booklist_h3_title).text(title);
          const p_isbn = $("<p>").addClass(style_booklist_p_isbn).text(`ISBN: ${isbn}`);
          const p_author = $("<p>").addClass(style_booklist_p_author).text(`Author: ${author}`);
          const p_year = $("<p>").addClass(style_booklist_p_year).text(`Year: ${year}`);
          const div_action = $("<div>").addClass(style_booklist_div_action);
          const btn_edit = $("<button>").addClass(style_booklist_btn_edit).text("Edit book");
          const btn_swap = $("<button>").addClass(style_booklist_btn_swap).text("Finish book");
          const btn_del = $("<button>").addClass(style_booklist_btn_del).text("Delete book");
          div_action.append(btn_edit, btn_swap, btn_del);
          article.append(h3_title, p_isbn, p_author, p_year, div_action);
          bookList.append(article);
        });
        bookList.data('count', data.length);
        bookList.find('.no_data_found_msg').addClass('hidden');
      }
    },
    error: function (xhr, status, error) {
      console.error("Error fetching data:", error);
    }
  });

  $.ajax({
    url: "../public/php/read.php",
    type: "GET",
    data: {
      table_name: "book_finished",
      keyword: ""
    },
    dataType: "json",
    success: function (data) {
      const bookList = $("#book_finished");
      if (data.length > 0) {
        $.each(data, function (index, book) {
          const { isbn, title, author, year } = book;
          const article = $("<article>").addClass(style_booklist_article);
          const h3_title = $("<h3>").addClass(style_booklist_h3_title).text(title);
          const p_isbn = $("<p>").addClass(style_booklist_p_isbn).text(`ISBN: ${isbn}`);
          const p_author = $("<p>").addClass(style_booklist_p_author).text(`Author: ${author}`);
          const p_year = $("<p>").addClass(style_booklist_p_year).text(`Year: ${year}`);
          const div_action = $("<div>").addClass(style_booklist_div_action);
          const btn_edit = $("<button>").addClass(style_booklist_btn_edit).text("Edit book");
          const btn_swap = $("<button>").addClass(style_booklist_btn_swap).text("Re-read book");
          const btn_del = $("<button>").addClass(style_booklist_btn_del).text("Delete book");
          div_action.append(btn_edit, btn_swap, btn_del);
          article.append(h3_title, p_isbn, p_author, p_year, div_action);
          bookList.append(article);
        });
        bookList.data('count', data.length);
        bookList.find('.no_data_found_msg').addClass('hidden');
      }
    },
    error: function (xhr, status, error) {
      console.error("Error fetching data:", error);
    }
  });
});

$('#inputBook').submit(function (event) {
  event.preventDefault();
  isbn= $('#inputBook input[name="isbn"]').val();
  title= $('#inputBook input[name="title"]').val();
  author= $('#inputBook input[name="author"]').val();
  year= $('#inputBook input[name="year"]').val();
  table_name= $("#is_finished").is(':checked') ? "book_finished" : "book_unfinished";
  $.ajax({
    url: '../public/php/create.php',
    method: 'POST',
    data: {
      isbn:isbn,
      title:title,
      author:author,
      year:year,
      table_name:table_name
    },
    success: function (response) {
      console.log(response);
      bookList = $('#'+$("#is_finished").is(':checked') ? "book_finished" : "book_unfinished");
      bookList.find('.no_data_found_msg').addClass('hidden');

      const article = $("<article>").addClass(style_booklist_article);
      const h3_title = $("<h3>").addClass(style_booklist_h3_title).text(title);
      const p_isbn = $("<p>").addClass(style_booklist_p_isbn).text(`ISBN: ${isbn}`);
      const p_author = $("<p>").addClass(style_booklist_p_author).text(`Author: ${author}`);
      const p_year = $("<p>").addClass(style_booklist_p_year).text(`Year: ${year}`);
      const div_action = $("<div>").addClass(style_booklist_div_action);
      const btn_edit = $("<button>").addClass(style_booklist_btn_edit).text("Edit book");
      const btn_swap = $("<button>").addClass(style_booklist_btn_swap).text(table_name == "book_finished" ? "Re-read book" : "Finish book");
      const btn_del = $("<button>").addClass(style_booklist_btn_del).text("Delete book");
      div_action.append(btn_edit, btn_swap, btn_del);
      article.append(h3_title, p_isbn, p_author, p_year, div_action);
      bookList.append(article);
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
    url: '../public/php/delete.php',
    method: 'POST',
    data: {
      isbn: isbn,
      table_name: parentElement
    },
    success: function (response) {
      bookItem.remove();
      $(`#${parentElement}`).data('count', $(`#${parentElement}`).data('count') - 1);
      if($(`#${parentElement}`).data('count') == 0){
        $(`#${parentElement}`).find('p').removeClass('hidden');
      }
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
    url: '../public/php/create.php',
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
    url: '../public/php/delete.php',
    method: 'POST',
    data: {
      isbn: isbn,
      table_name: parentElement
    },
    success: function (response) {
      console.log(response);
      bookItem.find(".book_swap_shelf_btn").text(destinationShelf == "book_finished" ? "Re-read book" : "Finish book");
      $("#" + destinationShelf).append(bookItem);
      $(`#${parentElement}`).data('count', $(`#${parentElement}`).data('count') - 1);
      $(`#${destinationShelf}`).data('count', $(`#${destinationShelf}`).data('count') + 1);
      if($(`#${parentElement}`).data('count') == 0){
        $(`#${parentElement}`).find('.no_data_found_msg').removeClass('hidden');
      }
      $(`#${destinationShelf}`).find('.no_data_found_msg').addClass('hidden');
    },
    error: function (xhr, status, error) {
      console.error(xhr, status, error);
    }
  });
});

$(document).on('click', '.book_edit_btn', function () {
  console.log(this);
  $('#overlayer').removeClass("hidden");
  $('#edit_book').removeClass("hidden");
  $('#edit_book').addClass("flex");
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
    url: '../public/php/update.php',
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
  $('#edit_book').removeClass("flex");
  $('#overlayer').addClass("hidden");
  $('#edit_book').addClass("hidden");
});

$('#is_finished').on('click', function () {
  var id = parseInt($(this).val(), 10);
  if ($(this).is(":checked")) {
    $('#finished_label').text("Finished");
  } else {
    $('#finished_label').text("Unfinished");
  }
});

$("#search_keyword").on('input', function (event) {
  event.preventDefault();
  $("#book_unfinished").empty();
  $("#book_finished").empty();
  keyword = $("#search_keyword").val();
  $.ajax({
    url: "../public/php/read.php",
    type: "GET",
    data: {
      table_name: "book_unfinished",
      keyword: keyword
    },
    dataType: "json",
    success: function (data) {
      const bookList = $("#book_unfinished");
      bookList.append($('<p class="no_data_found_msg">No data found</p>'));
      if (data.length > 0) {
        $.each(data, function (index, book) {
          const { isbn, title, author, year } = book;
          const article = $("<article>").addClass(style_booklist_article);
          const h3_title = $("<h3>").addClass(style_booklist_h3_title).text(title);
          const p_isbn = $("<p>").addClass(style_booklist_p_isbn).text(`ISBN: ${isbn}`);
          const p_author = $("<p>").addClass(style_booklist_p_author).text(`Author: ${author}`);
          const p_year = $("<p>").addClass(style_booklist_p_year).text(`Year: ${year}`);
          const div_action = $("<div>").addClass(style_booklist_div_action);
          const btn_edit = $("<button>").addClass(style_booklist_btn_edit).text("Edit book");
          const btn_swap = $("<button>").addClass(style_booklist_btn_swap).text("Finish book");
          const btn_del = $("<button>").addClass(style_booklist_btn_del).text("Delete book");
          div_action.append(btn_edit, btn_swap, btn_del);
          article.append(h3_title, p_isbn, p_author, p_year, div_action);
          bookList.append(article);
        });
        bookList.data('count', data.length);
        bookList.find('.no_data_found_msg').addClass('hidden');
      }
    },
    error: function (xhr, status, error) {
      console.error("Error fetching data:", error);
    }
  });

  $.ajax({
    url: "../public/php/read.php",
    type: "GET",
    data: {
      table_name: "book_finished",
      keyword: keyword
    },
    dataType: "json",
    success: function (data) {
      const bookList = $("#book_finished");
      bookList.append($('<p class="no_data_found_msg">No data found</p>'));
      if (data.length > 0) {
        $.each(data, function (index, book) {
          const { isbn, title, author, year } = book;
          const article = $("<article>").addClass(style_booklist_article);
          const h3_title = $("<h3>").addClass(style_booklist_h3_title).text(title);
          const p_isbn = $("<p>").addClass(style_booklist_p_isbn).text(`ISBN: ${isbn}`);
          const p_author = $("<p>").addClass(style_booklist_p_author).text(`Author: ${author}`);
          const p_year = $("<p>").addClass(style_booklist_p_year).text(`Year: ${year}`);
          const div_action = $("<div>").addClass(style_booklist_div_action);
          const btn_edit = $("<button>").addClass(style_booklist_btn_edit).text("Edit book");
          const btn_swap = $("<button>").addClass(style_booklist_btn_swap).text("Re-read book");
          const btn_del = $("<button>").addClass(style_booklist_btn_del).text("Delete book");
          div_action.append(btn_edit, btn_swap, btn_del);
          article.append(h3_title, p_isbn, p_author, p_year, div_action);
          bookList.append(article);
        });
        bookList.data('count', data.length);
        bookList.find('.no_data_found_msg').addClass('hidden');
      }
    },
    error: function (xhr, status, error) {
      console.error("Error fetching data:", error);
    }
  });
});