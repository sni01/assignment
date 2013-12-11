Name Sha Ni     git username sni01
1. I used javascript files combined technology, first I used YUI-compressor to compress
   the javascript files(this decreased the file load size). In my assignment, there are
   originally five javascript files(jquery-1.9.1.min.js;draw.js;game.js;libraries.js;setup.js).
   I compressed them one by one, and results are as follows:
   draw.js  3kb => 3kb
   game.js  8kb => 4kb
   libraries.js  3kb => 3kb
   setup.js  6kb => 5kb
   Then, I moved all the javascript codes into one js file(frogger-min.js). This decrease the request
   times, which will increase the performance.
   Then, I used html5 offline Application Cache, this allows the javascript files loaded quicker.
2. add manifest="frogger_manifest.txt" in html tag.
3. I used speed tracer to test the performances
4. since I compressed the javascript files and combined them into one javascript file. The loading files size
   decreased 6kb, and loading time decrease 37ms(request time, but this will sometime become really significant
   since the request time will vary).
   formerly, all the files are load using 147ms, after the improvement the tested loading time is 120ms. so the 
   loading time improve 18.4%. and file loading size decrease 4%(after: 145866bytes ;before: 151983bytes). From
   javascript perspective decrease 25%(from 20kb to 15kb).
5. there are some problems with later javascript file maintenance, since all comments and space are filtered all,
   the files became extremely hard to read, so it is quite hard for other coders to modified and make improvement.