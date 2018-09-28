program InfinitePenguins;

//Program will find the mean of an array.

var
arrayList: array [1..10] of double;
k, count: integer;
a: double;

function Mean(list: array of double): double;
begin
    a:=0;
    for count:= 1 to length(list) do
    begin
        a := a + list[count];
    end;
    Mean:= a/length(list);
end;

begin
     begin
     for k:=1 to length(arrayList) do
          arrayList[k] := k;
     end;

writeln('Mean of array is: ', Mean(arrayList));
readln();

end.
