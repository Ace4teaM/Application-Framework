#include "result.h"

namespace afw{

Result::Result(int code,char* desc)
{
    this->desc = desc;
    this->code = code;
}

/**
  Retourne le code d'erreur
*/
int Result::getCode()
{
    return this->code;
}

/**
  Retourne la chaine de description de l'erreur
*/
char* Result::getInfo(){
    return this->desc;
}

/**
  Codes standards
*/
const IResult* err_ok     = (const IResult*)new Result(0,"ok");
const IResult* err_failed = (const IResult*)new Result(1,"failed");

}
