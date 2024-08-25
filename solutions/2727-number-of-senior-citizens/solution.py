class Solution(object):
    def countSeniors(self, details):
        count =int(0)
        for i in range(len(details)) :
            if details[i][11]>='6' and details[i][12]>='0' :
                if details[i][11]=='6' and details[i][12]=='0' :
                    continue
                count = count+1
        return count
