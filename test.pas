Program Somatorio

Var
   Ini, Fim, Conta, Soma: integer;
Begin
    Ini  := 0;
    Fim  := 0;
    Soma := 0;
    read( Ini, Fim );
    For Conta := Ini To Fim do begin
         Soma := Soma + Conta
    End;
    write( Soma );
End.
