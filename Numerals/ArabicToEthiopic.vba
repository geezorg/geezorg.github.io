Function ArabicToEthiopic(number As Integer) As String
    Dim numberString As String: numberString = CStr(number)
    Dim n As Integer: n = Len(numberString) - 1
        
    If ((n Mod 2) = 0) Then
        numberString = "0" & numberString
        n = n + 1
    End If
         
    Dim ETHIOPIC_ONE As String: ETHIOPIC_ONE = ChrW(&H1369)
    Dim ETHIOPIC_TEN As String: ETHIOPIC_TEN = ChrW(&H1372)
    Dim ETHIOPIC_HUNDRED As String: ETHIOPIC_HUNDRED = ChrW(&H137B)
    Dim ETHIOPIC_TEN_THOUSAND As String: ETHIOPIC_TEN_THOUSAND = ChrW(&H137C)
    Dim ethioNumberString As String: ethioNumberString = ""

    Dim asciiOne, asciiTen, ethioOne, ethioTen, sep As String
    Dim pos As Integer
     
    For place = n To 0 Step -1
            asciiOne = ""
            asciiTen = ""
            ethioOne = ""
            ethioTen = ""

            asciiTen = Strings.Mid(numberString, (n - place + 1), 1)
            place = place - 1
            asciiOne = Mid(numberString, (n - place + 1), 1)
            
            If (asciiOne <> "0") Then
                ethioOne = ChrW(AscW(ETHIOPIC_ONE) + (AscW(asciiOne) - AscW("1")))
            End If
                
            If (asciiTen <> "0") Then
               ethioTen = ChrW(AscW(ETHIOPIC_TEN) + (AscW(asciiTen) - AscW("1")))
            End If
            
            pos = (place Mod 4) / 2
            
            sep = ""
            If (place <> 0) Then
                If (pos = 0) Then
                    sep = ETHIOPIC_TEN_THOUSAND
                ElseIf ((ethioOne <> "") Or (ethioTen <> "")) Then
                    sep = ETHIOPIC_HUNDRED
                End If
            End If

            If ((ethioOne = ETHIOPIC_ONE) And (ethioTen = "") And (n > 1)) Then
                If ((sep = ETHIOPIC_HUNDRED) Or ((place + 1) = n)) Then
                    ethioOne = ""
                End If
            End If
            
            If (ethioTen <> "") Then ethioNumberString = ethioNumberString & ethioTen
            If (ethioOne <> "") Then ethioNumberString = ethioNumberString & ethioOne
            If (sep <> "") Then ethioNumberString = ethioNumberString & sep
    Next
         
    ArabicToEthiopic = ethioNumberString
End Function
