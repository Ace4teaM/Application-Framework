#include "result.h"

namespace qafw{

    QResult::QResult(int code,QString desc)
    {
        this->desc = desc;
        this->code = code;
    }

    /**
      Retourne le code d'erreur
    */
    int QResult::getCode()
    {
        return this->code;
    }

    /**
      Retourne la chaine de description de l'erreur
    */
    QString QResult::getInfo(){
        return this->desc;
    }

    /**
      Codes standards
    */
    const QResult* QResult::OK                 = new QResult(1,"no_error");
    const QResult* QResult::ERR_FAILED         = new QResult(0,"failed");
    const QResult* QResult::ERR_UNSUPORTED_CMD = new QResult(0,"unsuported_cmd");
}
