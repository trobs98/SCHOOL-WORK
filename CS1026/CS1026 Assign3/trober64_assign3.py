#
#Made by: Tyler Roberts
#This program is given a text file of tweets as well as keywords. It then calculates the sentiment of the tweets per time zone
#
#

#For this function, I seperated the text from tweets.txt where it only returns the longitude, latitude and tweet text
def get_segments(line):
    tweetend = line.find(']')
    latlong = line[1:tweetend]
    alloflatlong = latlong.split(',')
    part1 = line.find(' ', tweetend)
    part2 = line.find(' ', part1+1)
    part3 = line.find(' ', part2+1)
    part4 = line.find(' ', part3+1)
    tweettext = line[part4:]
    tweettext = tweettext.lstrip().rstrip()
    return [alloflatlong, tweettext]

#For this function, I took in the latitude and longitude that we got from seperating the text and use it to detetmine which time zone it is in
def FindTimeZone(latlong,a, b, c, d):
    lat = float(latlong[0])
    long = float(latlong[1])
    if lat<a[0] and lat>b[0] and long < a[1] and long > c[1]:
        return True
    else:
        return False

# I first made a list to store all of the keywords that belong to each sentiment. Ex. love has sentiment of 10 so it is stored in keywords10.
#I did this by seperating the keyword and sentiment at the comma and determing which sentiment goes with which keyword so I can store them in their respective lists
keywords5 = []
keywords10 =[]
keywords1 = []
def openkeywordfile(keywordfile):
    keywordfile = open(keywordfile, 'r', encoding='utf-8')
    for line in keywordfile:
        linesplit = line.split(',')
        linevalue = linesplit[1]
        linevalue = linevalue.replace('\n','')
        linekeyword = linesplit[0]
        if linevalue == '10':
            keywords10.append(linekeyword)
        elif linevalue == '5':
            keywords5.append(linekeyword)
        elif linevalue == '1':
            keywords1.append(linekeyword)

#I first made lists for the sentiments average per tweet and stored them in their respective time zones
#I then calculated the average of the lists to determine the average time zone sentiment
Eastern_Time_Sentiments = []
Mountain_Time_Sentiments = []
Central_Time_Sentiments = []
Pacific_Time_Sentiments = []
def Sentiment_Sum_PerTimeZone(sentimentslist):
    sum = 0
    for values in sentimentslist:
        values = float(values)
        sum = float(sum + values)
    SentimentValuepertimezone = round((sum / len(sentimentslist)),2)
    return (SentimentValuepertimezone)

#I used the tweets list that are made when reading the file and went through each word in each tweet to determine if the words matched the keywords
#When I went through each tweet, I appended the sentiment values to a list which was all of the sentiments for that particular tweet
#I then found the average sentiment score in each tweet and appended that to the sentiment lists
def GetSentimentValue(tweetslist, sentimentlists):
    for tweets in tweetslist:
        allwords = tweets.split(',')
        for words in allwords:
            newwords= words.split(" ")
            list = []
            for everyword in newwords:
                SentimentalValue = 0
                if everyword in keywords10:
                    SentimentalValue = 10
                    list.append(SentimentalValue)
                elif everyword in keywords5:
                    SentimentalValue = 5
                    list.append(SentimentalValue)
                elif everyword in keywords1:
                    SentimentalValue = 1
                    list.append(SentimentalValue)
            if len(list) != 0:
                sum = 0
                for values in list:
                    sum = sum + values
                averagepertweet = round((sum / len(list)),2)
                sentimentlists.append(averagepertweet)



#This is where I process and read the tweets.txt and use all of the functions above to get the total tweets per time zone and sentiments average per time zone
def readfile():
    #This is trying to open the tweet file and showing an exception if the tweet file does not exist
    try:
        tweetfile = input("Enter the tweet file name: ")
        infile = open(tweetfile, 'r',encoding='utf-8')
    except IOError:
        print("File does not exist")
        return

    P1 = (49.189787, -67.444574)
    P2 = (24.660845, -67.444574)
    P3 = (49.189787, -87.518395)
    P4 = (24.660845, -87.518395)
    P5 = (49.189787, -101.998892)
    P6 = (24.660845, -101.998892)
    P7 = (49.189787, -115.236428)
    P8 = (24.660845, -115.236428)
    P9 = (49.189787, -125.242264)
    P10 = (24.660845, -125.242264)

    #Here is where all the tweets are stored if they are in the respective time zone
    Eastern_Time_Tweets = []
    Mountain_Time_Tweets = []
    Central_Time_Tweets = []
    Pacific_Time_Tweets = []

    #This is trying to open the keywords file and showing an exception if the file does not exist
    try:
        keywordfile = input("Enter the keywords file: ")
        openkeywordfile(keywordfile)
    except IOError:
        print("File does not exist")
        return

    #This is where we process the longitude and latitude of each tweet, determining which time zone it's in and placing the tweet in the respective time zone tweets list
    for line in infile:
        lineparts = get_segments(line)
        if FindTimeZone(lineparts[0],P1,P2,P3,P4):
            Eastern_Time_Tweets.append(lineparts[1])
        elif FindTimeZone(lineparts[0],P3,P4,P5,P6):
            Central_Time_Tweets.append(lineparts[1])
        elif FindTimeZone(lineparts[0],P5,P6,P7,P8):
            Mountain_Time_Tweets.append(lineparts[1])
        elif FindTimeZone(lineparts[0],P7, P8,P9,P10):
            Pacific_Time_Tweets.append(lineparts[1])

    #Here it is finding the sentiment score of the tweets in the time zone tweets list and appending those scores to the time zone sentiments list
    GetSentimentValue(Eastern_Time_Tweets, Eastern_Time_Sentiments)
    GetSentimentValue(Central_Time_Tweets, Central_Time_Sentiments)
    GetSentimentValue(Mountain_Time_Tweets, Mountain_Time_Sentiments)
    GetSentimentValue(Pacific_Time_Tweets, Pacific_Time_Sentiments)

    #Here we are printing the length of the time zone sentiments list which prints the amount of tweets in that time zone that HAVE keywords within them
    print("The number of tweets in Eastern Time is", len(Eastern_Time_Sentiments))
    print("The number of tweets in Central Time is", len(Central_Time_Sentiments))
    print("The number of tweets in Mountain Time is", len(Mountain_Time_Sentiments))
    print("The number of tweets in Pacific Time is", len(Pacific_Time_Sentiments))
    #Here we are printing the total average sentiment values for each time zone
    print("The Sentiment value for Eastern Time is", Sentiment_Sum_PerTimeZone(Eastern_Time_Sentiments))
    print("The Sentiment value for Central Time is", Sentiment_Sum_PerTimeZone(Central_Time_Sentiments))
    print("The sentiment value for Mountain Time is", Sentiment_Sum_PerTimeZone(Mountain_Time_Sentiments))
    print("The sentiment value for Pacific Time is", Sentiment_Sum_PerTimeZone(Pacific_Time_Sentiments))

    #This is the graphics part of the assignemnt where I imported happy histogram and inputted the total average sentiment values for each time zone to be displayed
    import happy_histogram
    happy_histogram.drawSimpleHistogram((Sentiment_Sum_PerTimeZone(Eastern_Time_Sentiments)),(Sentiment_Sum_PerTimeZone(Central_Time_Sentiments)),(Sentiment_Sum_PerTimeZone(Mountain_Time_Sentiments)),(Sentiment_Sum_PerTimeZone(Pacific_Time_Sentiments)))

    return

readfile()















