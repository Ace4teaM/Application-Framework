#ifndef QRESULT_H
#define QRESULT_H

#include <QString>
#include "../iface/iresult.h"

using namespace afw;

namespace qafw{

    class QResult : public afw::IResult<QString>
    {
    protected:
        int code;
        QString desc;
    public:
        QResult(int code,QString desc);
        int getCode();
        QString getInfo();

        static const QResult* OK;
        static const QResult* ERR_FAILED;
        static const QResult* ERR_UNSUPORTED_CMD;
    };


}

#endif // QRESULT_H
