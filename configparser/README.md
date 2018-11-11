0. `<Audio></Audio>` attributes are as below

      * `position` = '%top,%left,%width,%height' `0<=top,left,width,hight<=100`
      * `border` = `{none|dots|line}`
      * `bg-color` = backgroud color of the whole area -> `VALID COLOR CODE`
      * `bg-res` = background resource to drown
      * `res` = audio clip file uri

1. `<Video></Video>` attributes are as below

      * `position` = '%top,%left,%width,%height' `0<=top,left,width,hight<=100`
      * `border` = `{none|dots|line}`
      * `bg-color` = backgroud color of the whole area -> `VALID COLOR CODE`
      * `bg-res` = background resource to drown
      * `res` = video clip file uri

2. `<Text></Text>` **Text** tag
      * `font-name`= `{}`
      * `border` = `{none|dots|line}`
      * `bg-color` = backgroud color of the whole area -> `VALID COLOR CODE`
      * `position` = '%top,%left,%width,%height' `0<=top,left,width,hight<=100`
      * `text-color` = TEXT color of the whole text -> `VALID COLOR CODE`
      * `angle` = rotate clock wise angle `0-180`
      * `highlightTextOnAudio` =  high light the text if player is playing audio {`true`|`false`}
      * `action-enable` = make action tag valid only if value is `true` {`true`|`false`}
      * `audio-res` = uri to audio file

3. `<p></p>` for **Paragraph**  attributes are below (nested to <Text> tag)
    * `pg-sp` = paragraph Spacing
    * `ls` = line spacing
    * `hi` = head indent
    * `li` = line indent

4. `<ac></ac>` **Action** on tap (nested to <Text> tag)
     * `audio-time` = millis
     * `video-clip` = 'resource identifier'
     * `popup` = 'resource identifier'

5. `<b></b>` **Bold word** (nested to <Text> tag)

6. `<i></i>` **Italic word** (nested to <Text> tag)

7.  `<sh></sh>` **Shadow color for text** (nested to <Text> tag)
    * `c`= color

8. `<bg></bg>` set **Label back ground color for text** (nested to <Text> tag)
    * `c` = color
