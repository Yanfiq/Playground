<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pattern</title>
    <style>
        .box {
            margin: 0;
            width: 100px;
            height: 100px;
        }
        .nothing {
            background-color: transparent;
        }
        .yellow {
            background-color: rgb(255, 255, 101);
        }
        .black {
            background-color: rgb(0, 0, 0);
        }
    </style>
</head>
<body>
    <form name="form" action="" method="get">
        <input type="text" name="subject" id="subject">
    </form>

    <?php
        $size = $_GET['subject'];
        $isYellow = true;
        echo "<div style='display: flex; flex-direction: column'>";
        for ($i = 0; $i < $size; $i++) {
            echo "<div style='display: flex; flex-direction: row'>";
            for ($j = 0; $j < $size; $j++) {
                if ($j < $size - $i - 1) {
                    echo "<div class='box nothing'></div>";
                } else {
                    $colorClass = $isYellow ? 'yellow' : 'black';
                    echo "<div class='box $colorClass'></div>";
                    $isYellow = !$isYellow;
                }
            }
            echo "</div>";
            $isYellow = true;
        }
        echo "</div>";
    ?>    
</body>
</html>



