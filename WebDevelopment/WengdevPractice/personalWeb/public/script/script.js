$(document).ready(function() {
  const $inputMsg = $('#inputMsg');
  const inputMaxLengthOnLoad = $inputMsg.attr('maxlength');
  $('#sisaKarakter').text(inputMaxLengthOnLoad);

  $inputMsg.on('input', function() {
    const jumlahKarakterDiketik = $inputMsg.val().length;
    const jumlahKarakterMaksimal = $inputMsg.attr('maxlength');

    const sisaKarakterUpdate = jumlahKarakterMaksimal - jumlahKarakterDiketik;
    $('#sisaKarakter').text(sisaKarakterUpdate.toString());

    if (sisaKarakterUpdate === 0) {
      $('#sisaKarakter').text('Batas maksimal tercapai!');
    } else if (sisaKarakterUpdate <= 5) {
      $('#notifikasiSisaKarakter').css('color', 'red');
    } else {
      $('#notifikasiSisaKarakter').css('color', 'white');
    }
  });

  $inputMsg.on('focus', function() {
    $('#notifikasiSisaKarakter').css('visibility', 'visible');
  }); 

  $inputMsg.on('blur', function() {
    $('#notifikasiSisaKarakter').css('visibility', 'hidden');
  });

  $.ajax({
    url: './data/projects.xml',
    type: 'GET',
    dataType: 'xml',
    success: function(xmlDoc) {
      const $items = $(xmlDoc).find('project');
      const $dataContainer = $('#projectsList');

      $items.each(function(index, item) {
        const $item = $(item);

        const $projectContainer = $('<li>', {
          class: 'flex flex-row grow mx-10 my-5 bg-slate-800 p-10 rounded'
        });

        const $info = $('<div>', { class: 'flex flex-col grow' });

        const $projectName = $('<h3>', {
          class: 'text-white font-bold text-lg',
          text: $item.find('projectName').text(),
        });
        $info.append($projectName);

        const $projectDesc = $('<p>', {
          class: 'text-white grow text-lg',
          text: $item.find('projectDesc').text()
        });
        $info.append($projectDesc);

        const $projectLink = $('<a>', {
          href: $item.find('projectLink').text(),
          text: 'SEE IN GITHUB',
          class: 'my-6 p-3 w-max bg-blue-400 border-blue-400 border-2 rounded duration-300 hover:bg-transparent hover:text-white hover:duration-200'
        });
        $info.append($projectLink);

        const $projectPict = $('<img>', {
          src: $item.find('projectPict').text(),
          alt: $projectName.text(),
          class: 'w-2/6 object-contain hover:duration-300 duration-300 hover:relative hover:-z-0 hover:scale-110'
        });

        $projectContainer.append($info, $projectPict);
        $dataContainer.append($projectContainer);
      });
    },
    error: function() {
      console.error('Failed to load XML file');
    }
  });
});